package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.DietLabelResponseDto;
import hr.kbratko.cookmate.model.DietLabel;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DietLabelToDietLabelResponseDtoConverter implements Converter<DietLabel, DietLabelResponseDto> {

  @Override
  public DietLabelResponseDto convert(@NonNull DietLabel source) {
    return new DietLabelResponseDto(
      source.getId(),
      source.getName()
    );
  }

}
