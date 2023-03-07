package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.PrivilegeResponseDto;
import hr.kbratko.cookmate.model.Privilege;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeToPrivilegeResponseDtoConverter implements Converter<Privilege, PrivilegeResponseDto> {

  @Override
  public PrivilegeResponseDto convert(@NonNull Privilege source) {
    return new PrivilegeResponseDto(
      source.getId(),
      source.getName()
    );
  }

}
