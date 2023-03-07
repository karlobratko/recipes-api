package hr.kbratko.cookmate.dto.request;

import hr.kbratko.cookmate.model.FoodCategory;
import jakarta.validation.constraints.Size;

public record UpdateFoodRequestDto(
  @Size(min = 2, max = 100, message = "Food name must be between 2 and 100 characters long.")
  String name,
  FoodCategory category
) {
}
