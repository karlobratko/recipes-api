package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.FieldNameConstants;

public record LoginRequestDto(
  @NotNull(message = "User username is a mandatory field.")
  @Size(max = 50, message = "User username must not exceed 50 characters.")
  String username,
  @NotNull(message = "User password is a mandatory field.")
  @Size(max = 255, message = "User password must not exceed 255 characters.")
  String password
) {
}
