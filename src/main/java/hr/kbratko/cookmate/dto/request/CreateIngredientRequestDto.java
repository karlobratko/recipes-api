package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateIngredientRequestDto(
  @NotNull(message = "Ingredient foodId is a mandatory field.")
  @Positive(message = "Ingredient foodId must be greater than 0.")
  Long foodId,
  @NotNull(message = "Ingredient quantity is a mandatory field.")
  @Positive(message = "Ingredient quantity must be greater than 0.")
  BigDecimal quantity,
  @Size(min = 2, max = 50, message = "Ingredient measure must be between 2 and 50 characters long.")
  String measure
) {
}
