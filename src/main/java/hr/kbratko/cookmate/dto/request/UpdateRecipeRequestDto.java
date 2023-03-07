package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.validator.constraints.URL;

public record UpdateRecipeRequestDto(
  @Size(min = 5, max = 250, message = "Recipe title must be between 5 and 250 characters long.")
  String title,
  @Size(min = 5, max = 250, message = "Recipe source must be between 5 and 250 characters long.")
  String source,
  @Size(max = 2048, message = "Recipe source must not exceed 2048 characters.")
  @URL(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]|null", message = "Recipe url must be valid url address.")
  String url,
  @Positive(message = "Recipe calories must be greater than 0.")
  BigDecimal calories,
  @Positive(message = "Recipe prepTime must be greater than 0.")
  BigDecimal prepTime,
  List<Long> dietLabels,
  List<Long> healthLabels,
  List<Long> cautions,
  List<Long> ingredients
) {
}
