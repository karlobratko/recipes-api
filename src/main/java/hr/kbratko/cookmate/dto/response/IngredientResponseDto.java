package hr.kbratko.cookmate.dto.response;

import hr.kbratko.cookmate.model.Food;
import java.math.BigDecimal;

public record IngredientResponseDto(
  Long id,
  FoodResponseDto food,
  BigDecimal quantity,
  String measure
) {
}
