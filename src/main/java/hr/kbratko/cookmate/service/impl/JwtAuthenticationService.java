package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.LoginRequestDto;
import hr.kbratko.cookmate.dto.request.RegisterRequestDto;
import hr.kbratko.cookmate.dto.response.AuthenticationResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.repository.UserRepository;
import hr.kbratko.cookmate.service.AuthenticationService;
import hr.kbratko.cookmate.service.JwtUserTokenService;
import jakarta.transaction.Transactional;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService implements AuthenticationService {

  private final UserRepository userRepository;

  private final JwtUserTokenService jwtUserTokenService;

  private final AuthenticationManager authenticationManager;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public AuthenticationResponseDto register(RegisterRequestDto requestDto) {
    val user = userRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, User.class)));

    return conversionService.convert(jwtUserTokenService.generate(user), AuthenticationResponseDto.class);
  }

  @Override
  @Transactional
  public AuthenticationResponseDto login(LoginRequestDto requestDto) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          requestDto.username(),
          requestDto.password()
        )
      );
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Wrong username or password.");
    }

    val user = userRepository.findByUsername(requestDto.username())
      .orElseThrow(() -> new NotFoundException("Couldn't find user %s.".formatted(requestDto.username())));

    return conversionService.convert(jwtUserTokenService.generate(user), AuthenticationResponseDto.class);
  }

}
