package hr.kbratko.cookmate.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateHealthLabelRequestDto(
  @NotNull(message = "Health label name is a mandatory field.")
  @Size(min = 5, max = 100, message = "Health label name must be between 5 and 100 characters long.")
  String name
) {
}
