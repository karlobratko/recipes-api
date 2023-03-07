package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.CreateHealthLabelRequestDto;
import hr.kbratko.cookmate.model.HealthLabel;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateHealthLabelRequestDtoToHealthLabelConverter implements Converter<CreateHealthLabelRequestDto, HealthLabel> {

  @Override
  public HealthLabel convert(@NonNull CreateHealthLabelRequestDto source) {
    return HealthLabel.builder()
      .name(source.name())
      .build();
  }

}
