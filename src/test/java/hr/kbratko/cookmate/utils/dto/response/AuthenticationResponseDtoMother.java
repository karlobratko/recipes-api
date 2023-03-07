package hr.kbratko.cookmate.utils.dto.response;

import hr.kbratko.cookmate.dto.response.AuthenticationResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationResponseDtoMother {

  private static final AuthenticationResponseDto INSTANCE;

  static {
    INSTANCE = new AuthenticationResponseDto("token");
  }

  public static AuthenticationResponseDto completeAndBuilt() {
    return INSTANCE;
  }

}
