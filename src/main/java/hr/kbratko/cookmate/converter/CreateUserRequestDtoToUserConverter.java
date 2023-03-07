package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.CreateUserRequestDto;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.repository.RoleRepository;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserRequestDtoToUserConverter implements Converter<CreateUserRequestDto, User> {

  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  @Override
  public User convert(@NonNull CreateUserRequestDto source) {
    return User.builder()
      .firstName(source.firstName())
      .lastName(source.lastName())
      .username(source.username())
      .email(source.email())
      .password(passwordEncoder.encode(source.password()))
      .roles(Set.copyOf(roleRepository.findAllById(source.roles())))
      .build();
  }

}
