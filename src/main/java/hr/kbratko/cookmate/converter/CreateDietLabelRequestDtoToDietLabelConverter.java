package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.CreateDietLabelRequestDto;
import hr.kbratko.cookmate.model.DietLabel;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateDietLabelRequestDtoToDietLabelConverter implements Converter<CreateDietLabelRequestDto, DietLabel> {

  @Override
  public DietLabel convert(@NonNull CreateDietLabelRequestDto source) {
    return DietLabel.builder()
      .name(source.name())
      .build();
  }

}
