package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.HealthLabelResponseDto;
import hr.kbratko.cookmate.model.HealthLabel;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HealthLabelToHealthLabelDtoConverter implements Converter<HealthLabel, HealthLabelResponseDto> {

  @Override
  public HealthLabelResponseDto convert(@NonNull HealthLabel source) {
    return new HealthLabelResponseDto(
      source.getId(),
      source.getName()
    );
  }

}
