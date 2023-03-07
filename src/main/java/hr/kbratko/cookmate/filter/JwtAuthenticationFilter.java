package hr.kbratko.cookmate.filter;

import hr.kbratko.cookmate.constants.JwtConstants;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.service.JwtUserTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;

  private final JwtUserTokenService jwtTokenService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader(JwtConstants.authorizationHeader);

    if (Objects.isNull(authHeader) || !authHeader.startsWith(JwtConstants.headerSchema)) {
      filterChain.doFilter(request, response);
      return;
    }

    val jwtToken = authHeader.substring(7);
    val username = jwtTokenService.getUsername(jwtToken);
    if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      val user = (User) userDetailsService.loadUserByUsername(username);

      if (jwtTokenService.isValid(jwtToken, user)) {
        val authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }

}
