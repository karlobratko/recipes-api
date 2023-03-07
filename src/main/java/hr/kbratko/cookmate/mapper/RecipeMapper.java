package hr.kbratko.cookmate.mapper;

import hr.kbratko.cookmate.dto.request.UpdateRecipeRequestDto;
import hr.kbratko.cookmate.model.Caution;
import hr.kbratko.cookmate.model.DietLabel;
import hr.kbratko.cookmate.model.HealthLabel;
import hr.kbratko.cookmate.model.Ingredient;
import hr.kbratko.cookmate.model.Recipe;
import hr.kbratko.cookmate.repository.CautionRepository;
import hr.kbratko.cookmate.repository.DietLabelRepository;
import hr.kbratko.cookmate.repository.HealthLabelRepository;
import hr.kbratko.cookmate.repository.IngredientRepository;
import java.util.List;
import java.util.Set;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
  componentModel = "spring",
  uses = {
    DietLabelRepository.class,
    HealthLabelRepository.class,
    CautionRepository.class,
    IngredientRepository.class
  },
  injectionStrategy = InjectionStrategy.FIELD
)
public abstract class RecipeMapper {

  @Autowired
  private DietLabelRepository dietLabelRepository;

  @Autowired
  private HealthLabelRepository healthLabelRepository;

  @Autowired
  private CautionRepository cautionRepository;

  @Autowired
  private IngredientRepository ingredientRepository;

  @Named("mapListOfIdsToSetOfDietLabels")
  protected Set<DietLabel> mapListOfIdsToSetOfDietLabels(List<Long> value) {
    return Set.copyOf(dietLabelRepository.findAllById(value));
  }

  @Named("mapListOfIdsToSetOfHealthLabels")
  protected Set<HealthLabel> mapListOfIdsToSetOfHealthLabels(List<Long> value) {
    return Set.copyOf(healthLabelRepository.findAllById(value));
  }

  @Named("mapListOfIdsToSetOfCautions")
  protected Set<Caution> mapListOfIdsToSetOfCautions(List<Long> value) {
    return Set.copyOf(cautionRepository.findAllById(value));
  }

  @Named("mapListOfIdsToSetOfIngredients")
  protected Set<Ingredient> mapListOfIdsToSetOfIngredients(List<Long> value) {
    return Set.copyOf(ingredientRepository.findAllById(value));
  }

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(source = "dietLabels", target = Recipe.Fields.dietLabels, qualifiedByName = {"mapListOfIdsToSetOfDietLabels"})
  @Mapping(source = "healthLabels", target = Recipe.Fields.healthLabels, qualifiedByName = {"mapListOfIdsToSetOfHealthLabels"})
  @Mapping(source = "cautions", target = Recipe.Fields.cautions, qualifiedByName = {"mapListOfIdsToSetOfCautions"})
  @Mapping(source = "ingredients", target = Recipe.Fields.ingredients, qualifiedByName = {"mapListOfIdsToSetOfIngredients"})
  public abstract Recipe updateRecipeWithUpdateRecipeRequestDto(@MappingTarget Recipe recipe, UpdateRecipeRequestDto requestDto);

}
