package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
  @NotNull(message = "User first name is a mandatory field.")
  @Size(min = 2, max = 50, message = "User first name must be between 2 and 50 characters long.")
  String firstName,
  @NotNull(message = "User last name is a mandatory field.")
  @Size(min = 2, max = 50, message = "User last name must be between 2 and 50 characters long.")
  String lastName,
  @NotNull(message = "User username is a mandatory field.")
  @Size(min = 5, max = 50, message = "User username must be between 5 and 50 characters long.")
  String username,
  @NotNull(message = "User email is a mandatory field.")
  @Size(max = 254, message = "User email must not exceed 254 characters.")
  @Email(message = "User email must be valid email address.")
  String email,
  @NotNull(message = "User password is a mandatory field.")
  @Size(min = 8, message = "User password must be at least 8 characters long.")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,255}$", message = "User password must contain at lest one digit, lowercase character, uppercase character, special character and not contain whitespace.")
  String password
) {
}
