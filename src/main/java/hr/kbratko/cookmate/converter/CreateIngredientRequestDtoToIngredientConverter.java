package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.CreateIngredientRequestDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.model.Ingredient;
import hr.kbratko.cookmate.repository.FoodRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateIngredientRequestDtoToIngredientConverter implements Converter<CreateIngredientRequestDto, Ingredient> {

  private final FoodRepository foodRepository;

  @Override
  public Ingredient convert(@NonNull CreateIngredientRequestDto source) {
    return Ingredient.builder()
      .food(foodRepository.findById(source.foodId()).orElseThrow(() -> new NotFoundException(Constants.foodNotFoundMessageFormat.formatted(source.foodId()))))
      .quantity(source.quantity())
      .measure(source.measure())
      .build();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  static class Constants {

    public static final String foodNotFoundMessageFormat = "Couldn't find food with id %d.";

  }

}
