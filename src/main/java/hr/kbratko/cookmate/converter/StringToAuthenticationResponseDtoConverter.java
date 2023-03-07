package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.AuthenticationResponseDto;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAuthenticationResponseDtoConverter implements Converter<String, AuthenticationResponseDto> {

  @Override
  public AuthenticationResponseDto convert(@NonNull String source) {
    return new AuthenticationResponseDto(source);
  }

}
