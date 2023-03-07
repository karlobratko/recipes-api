package hr.kbratko.cookmate.service;

import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtTokenService<T> {

  String getSubject(String token);

  Date getExpiration(String token);

  <C> C getClaim(String token, Function<Claims, C> claimResolver);

  String generate(T user);

  String generate(String subject, Map<String, Object> extraClaims);

  boolean isValid(String token, T data);

}
