package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UpdateIngredientRequestDto(
  @Positive(message = "Ingredient foodId must be greater than 0.")
  Long foodId,
  @Positive(message = "Ingredient quantity must be greater than 0.")
  BigDecimal quantity,
  @Size(min = 2, max = 50, message = "Ingredient measure must be between 2 and 50 characters long.")
  String measure
) {
}
