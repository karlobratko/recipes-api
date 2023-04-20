package hr.kbratko.cookmate.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtConstants {

  public static final String authorizationHeader = "Authorization";

  public static final String headerSchema = "Bearer";

}
