package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.CautionResponseDto;
import hr.kbratko.cookmate.model.Caution;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CautionToCautionResponseDtoConverter implements Converter<Caution, CautionResponseDto> {

  @Override
  public CautionResponseDto convert(@NonNull Caution source) {
    return new CautionResponseDto(
      source.getId(),
      source.getName()
    );
  }

}
