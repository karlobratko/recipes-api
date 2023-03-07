package hr.kbratko.cookmate.utils.dto.request;

import hr.kbratko.cookmate.dto.request.LoginRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDtoMother {

  private static final LoginRequestDto INSTANCE;

  static {
    INSTANCE = new LoginRequestDto("username", "password");
  }

  public static LoginRequestDto completeAndBuilt() {
    return INSTANCE;
  }

}
