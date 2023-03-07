package hr.kbratko.cookmate.dto.response;

public record FoodResponseDto(
  Long id,
  String name,
  String category
) {
}
