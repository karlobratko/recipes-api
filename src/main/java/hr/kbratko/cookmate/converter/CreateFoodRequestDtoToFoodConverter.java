package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.CreateFoodRequestDto;
import hr.kbratko.cookmate.model.Food;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateFoodRequestDtoToFoodConverter implements Converter<CreateFoodRequestDto, Food> {

  @Override
  public Food convert(@NonNull CreateFoodRequestDto source) {
    return Food.builder()
      .name(source.name())
      .category(source.category())
      .build();
  }

}
