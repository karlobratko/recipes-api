package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.response.RecipeResponseDto;
import hr.kbratko.cookmate.model.Recipe;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeToRecipeResponseDtoConverter implements Converter<Recipe, RecipeResponseDto> {

  private final DietLabelToDietLabelResponseDtoConverter dietLabelToDietLabelResponseDtoConverter;

  private final HealthLabelToHealthLabelDtoConverter healthLabelToHealthLabelDtoConverter;

  private final CautionToCautionResponseDtoConverter cautionToCautionResponseDtoConverter;

  private final IngredientToIngredientResponseDtoConverter ingredientToIngredientResponseDtoConverter;

  @Override
  public RecipeResponseDto convert(@NonNull Recipe source) {
    return new RecipeResponseDto(
      source.getId(),
      source.getTitle(),
      source.getSource(),
      source.getUrl(),
      source.getCalories(),
      source.getPrepTime(),
      source.getDietLabels().stream().map(dietLabelToDietLabelResponseDtoConverter::convert).toList(),
      source.getHealthLabels().stream().map(healthLabelToHealthLabelDtoConverter::convert).toList(),
      source.getCautions().stream().map(cautionToCautionResponseDtoConverter::convert).toList(),
      source.getIngredients().stream().map(ingredientToIngredientResponseDtoConverter::convert).toList()
    );
  }

}
