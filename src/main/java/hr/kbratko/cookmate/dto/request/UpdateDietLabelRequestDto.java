package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateDietLabelRequestDto(
  @Size(min = 5, max = 100, message = "Diet label name must be between 5 and 100 characters long.")
  String name
) {
}
