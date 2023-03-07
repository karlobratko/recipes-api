package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.JwtDto;
import hr.kbratko.cookmate.model.User;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UserToJwtDtoConverter implements Converter<User, JwtDto> {

  @Override
  public JwtDto convert(@NonNull User source) {
    return new JwtDto(
      source.getUsername(),
      source.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
    );
  }

}
