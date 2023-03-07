package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.FoodCategoryResponseDto;
import hr.kbratko.cookmate.model.FoodCategory;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FoodCategoryToFoodCategoryResponseDtoConverter implements Converter<FoodCategory, FoodCategoryResponseDto> {

  @Override
  public FoodCategoryResponseDto convert(@NonNull FoodCategory source) {
    return new FoodCategoryResponseDto(source.name());
  }

}
