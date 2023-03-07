package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.FoodResponseDto;
import hr.kbratko.cookmate.dto.response.IngredientResponseDto;
import hr.kbratko.cookmate.model.Ingredient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientToIngredientResponseDtoConverter implements Converter<Ingredient, IngredientResponseDto> {

  private final FoodToFoodResponseDtoConverter foodToFoodResponseDtoConverter;

  @Override
  public IngredientResponseDto convert(@NonNull Ingredient source) {
    return new IngredientResponseDto(
      source.getId(),
      foodToFoodResponseDtoConverter.convert(source.getFood()),
      source.getQuantity(),
      source.getMeasure()
    );
  }

}
