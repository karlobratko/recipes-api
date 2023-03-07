package hr.kbratko.cookmate.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import java.security.Key;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "hr.kbratko.cookmate.security.jwt")
public record JwtProperties(

  @DurationUnit(ChronoUnit.MILLIS)
  Duration validityDurationInMs,

  @NotBlank
  String secret

) {

  public Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

}
