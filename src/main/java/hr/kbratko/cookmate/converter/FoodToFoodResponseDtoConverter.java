package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.FoodResponseDto;
import hr.kbratko.cookmate.model.Food;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FoodToFoodResponseDtoConverter implements Converter<Food, FoodResponseDto> {

  @Override
  public FoodResponseDto convert(Food source) {
    return new FoodResponseDto(
      source.getId(),
      source.getName(),
      source.getCategory().toString()
    );
  }

}
