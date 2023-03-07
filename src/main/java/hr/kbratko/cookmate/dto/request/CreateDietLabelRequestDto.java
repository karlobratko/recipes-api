package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDietLabelRequestDto(
  @NotNull(message = "Diet label name is a mandatory field.")
  @Size(min = 5, max = 100, message = "Diet label name must be between 5 and 100 characters long.")
  String name
) {
}
