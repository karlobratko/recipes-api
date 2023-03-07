package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.CreateCautionRequestDto;
import hr.kbratko.cookmate.model.Caution;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateCautionRequestDtoToCautionConverter implements Converter<CreateCautionRequestDto, Caution> {

  @Override
  public Caution convert(@NonNull CreateCautionRequestDto source) {
    return Caution.builder()
      .name(source.name())
      .build();
  }

}
