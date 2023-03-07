package hr.kbratko.cookmate.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record RecipeResponseDto(
  Long id,
  String title,
  String source,
  String url,
  BigDecimal calories,
  List<DietLabelResponseDto> dietLabels,
  List<HealthLabelResponseDto> healthLabels,
  List<CautionResponseDto> cautions,
  List<IngredientResponseDto> ingredients
) {
}
