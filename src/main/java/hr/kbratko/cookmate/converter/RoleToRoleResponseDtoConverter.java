package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.RoleResponseDto;
import hr.kbratko.cookmate.model.Role;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleToRoleResponseDtoConverter implements Converter<Role, RoleResponseDto> {

  private final PrivilegeToPrivilegeResponseDtoConverter privilegeToPrivilegeResponseDtoConverter;

  @Override
  public RoleResponseDto convert(@NonNull Role source) {
    return new RoleResponseDto(
      source.getId(),
      source.getName(),
      source.getPrivileges().stream().map(privilegeToPrivilegeResponseDtoConverter::convert).toList()
    );
  }

}
