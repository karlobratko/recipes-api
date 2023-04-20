package hr.kbratko.cookmate.mapper;

import hr.kbratko.cookmate.dto.request.UpdateIngredientRequestDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.model.Food;
import hr.kbratko.cookmate.model.Ingredient;
import hr.kbratko.cookmate.repository.FoodRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {FoodRepository.class}, injectionStrategy = InjectionStrategy.FIELD)
public abstract class IngredientMapper {

  @Autowired
  private FoodRepository foodRepository;

  @Named("mapFoodIdToFood")
  protected Food mapFoodIdToFood(Long value) {
    return foodRepository.findById(value).orElseThrow(() -> new NotFoundException(Constants.foodNotFoundMessageFormat.formatted(value)));
  }

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  @Mapping(source = "foodId", target = "food", qualifiedByName = {"mapFoodIdToFood"})
  public abstract Ingredient updateIngredientWithUpdateIngredientRequestDto(@MappingTarget Ingredient recipe, UpdateIngredientRequestDto requestDto);

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  class Constants {

    public static final String foodNotFoundMessageFormat = "Couldn't find food with id %d.";

  }

}
