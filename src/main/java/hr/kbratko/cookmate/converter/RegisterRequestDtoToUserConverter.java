package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.RegisterRequestDto;
import hr.kbratko.cookmate.exception.DatabaseEnumException;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.model.codebook.Roles;
import hr.kbratko.cookmate.repository.RoleRepository;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterRequestDtoToUserConverter implements Converter<RegisterRequestDto, User> {

  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  @Override
  public User convert(@NonNull RegisterRequestDto source) {
    return User.builder()
      .firstName(source.firstName())
      .lastName(source.lastName())
      .username(source.username())
      .email(source.email())
      .password(passwordEncoder.encode(source.password()))
      .roles(Set.of(roleRepository.findByName(Roles.USER.name()).orElseThrow(() -> new DatabaseEnumException(Constants.roleNotFoundMessageTemplate.formatted(Roles.USER.name())))))
      .build();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String roleNotFoundMessageTemplate = "Role %s not found in database.";

  }

}
