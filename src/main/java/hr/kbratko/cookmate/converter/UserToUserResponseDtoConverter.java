package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.UserResponseDto;
import hr.kbratko.cookmate.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

  private final RoleToRoleResponseDtoConverter roleToRoleResponseDtoConverter;

  @Override
  public UserResponseDto convert(@NonNull User source) {
    return new UserResponseDto(
      source.getId(),
      source.getFirstName(),
      source.getLastName(),
      source.getUsername(),
      source.getEmail(),
      source.getPassword(),
      source.getEnabled(),
      source.getRoles().stream().map(roleToRoleResponseDtoConverter::convert).toList()
    );
  }

}
