package hr.kbratko.cookmate.dto.response;

import hr.kbratko.cookmate.model.Role;
import java.util.List;

public record UserResponseDto(
  Long id,
  String firstName,
  String lastName,
  String username,
  String email,
  String password,
  Boolean enabled,
  List<RoleResponseDto> roles
) {
}
