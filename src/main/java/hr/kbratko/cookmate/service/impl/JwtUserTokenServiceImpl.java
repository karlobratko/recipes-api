package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.JwtDto;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.properties.JwtProperties;
import hr.kbratko.cookmate.service.JwtUserTokenService;
import hr.kbratko.cookmate.utils.ReflectionUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserTokenServiceImpl implements JwtUserTokenService {

  private final ConversionService conversionService;

  private final JwtProperties jwtProperties;

  @Override
  public String getSubject(String token) {
    return getClaim(token, Claims::getSubject);
  }

  @Override
  public String getUsername(String token) {
    return getSubject(token);
  }

  @Override
  public Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }

  @Override
  public <T> T getClaim(String token, Function<Claims, T> claimResolver) {
    return claimResolver.apply(getAllClaims(token));
  }

  @Override
  public String generate(User user) {
    return generate(
      user.getUsername(),
      ReflectionUtils.toMap(
        Objects.requireNonNull(conversionService.convert(user, JwtDto.class))
      )
    );
  }

  @Override
  public String generate(String subject, Map<String, Object> extraClaims) {
    return Jwts.builder()
      .setClaims(extraClaims)
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.validityDurationInMs().toMillis()))
      .signWith(jwtProperties.key())
      .compact();
  }

  @Override
  public boolean isValid(String token, User userDetails) {
    return getSubject(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private Claims getAllClaims(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(jwtProperties.key())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private boolean isTokenExpired(String token) {
    return getExpiration(token).before(new Date());
  }
}
