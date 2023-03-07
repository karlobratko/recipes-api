package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.validator.constraints.URL;

public record CreateRecipeRequestDto(
  @NotNull(message = "Recipe title is a mandatory field.")
  @Size(min = 5, max = 250, message = "Recipe title must be between 5 and 250 characters long.")
  String title,
  @NotNull(message = "Recipe source is a mandatory field.")
  @Size(min = 5, max = 250, message = "Recipe source must be between 5 and 250 characters long.")
  String source,
  @NotNull(message = "Recipe url is a mandatory field.")
  @Size(max = 2048, message = "Recipe source must not exceed 2048 characters.")
  @URL(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]|null", message = "Recipe url must be valid url address.")
  String url,
  @NotNull(message = "Recipe calories is a mandatory field.")
  @Positive(message = "Recipe calories must be greater than 0.")
  BigDecimal calories,
  @NotNull(message = "Recipe prepTime is a mandatory field.")
  @Positive(message = "Recipe prepTime must be greater than 0.")
  BigDecimal prepTime,
  List<Long> dietLabels,
  List<Long> healthLabels,
  List<Long> cautions,
  List<Long> ingredients
) {
}
