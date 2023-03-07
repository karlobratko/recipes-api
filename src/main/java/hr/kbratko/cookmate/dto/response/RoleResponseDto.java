package hr.kbratko.cookmate.dto.response;

import java.util.List;

public record RoleResponseDto(
  Long id,
  String name,
  List<PrivilegeResponseDto> privileges
) {
}
