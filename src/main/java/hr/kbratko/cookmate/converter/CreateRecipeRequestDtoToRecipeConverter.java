package hr.kbratko.cookmate.converter;

import hr.kbratko.cookmate.dto.request.CreateRecipeRequestDto;
import hr.kbratko.cookmate.model.Recipe;
import hr.kbratko.cookmate.repository.CautionRepository;
import hr.kbratko.cookmate.repository.DietLabelRepository;
import hr.kbratko.cookmate.repository.HealthLabelRepository;
import hr.kbratko.cookmate.repository.IngredientRepository;
import hr.kbratko.cookmate.repository.RoleRepository;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRecipeRequestDtoToRecipeConverter implements Converter<CreateRecipeRequestDto, Recipe> {

  private final DietLabelRepository dietLabelRepository;

  private final HealthLabelRepository healthLabelRepository;

  private final CautionRepository cautionRepository;

  private final IngredientRepository ingredientRepository;

  @Override
  public Recipe convert(@NonNull CreateRecipeRequestDto source) {
    return Recipe.builder()
      .title(source.title())
      .source(source.source())
      .url(source.url())
      .calories(source.calories())
      .prepTime(source.prepTime())
      .dietLabels(Set.copyOf(dietLabelRepository.findAllById(source.dietLabels())))
      .healthLabels(Set.copyOf(healthLabelRepository.findAllById(source.healthLabels())))
      .cautions(Set.copyOf(cautionRepository.findAllById(source.cautions())))
      .ingredients(Set.copyOf(ingredientRepository.findAllById(source.ingredients())))
      .build();
  }

}
