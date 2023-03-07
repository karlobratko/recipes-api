package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.CreateRecipeRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateRecipeRequestDto;
import hr.kbratko.cookmate.dto.response.CautionResponseDto;
import hr.kbratko.cookmate.dto.response.DietLabelResponseDto;
import hr.kbratko.cookmate.dto.response.HealthLabelResponseDto;
import hr.kbratko.cookmate.dto.response.IngredientResponseDto;
import hr.kbratko.cookmate.dto.response.RecipeResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.mapper.RecipeMapper;
import hr.kbratko.cookmate.model.Recipe;
import hr.kbratko.cookmate.repository.CautionRepository;
import hr.kbratko.cookmate.repository.DietLabelRepository;
import hr.kbratko.cookmate.repository.HealthLabelRepository;
import hr.kbratko.cookmate.repository.IngredientRepository;
import hr.kbratko.cookmate.repository.RecipeRepository;
import hr.kbratko.cookmate.service.RecipeService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  private final DietLabelRepository dietLabelRepository;

  private final HealthLabelRepository healthLabelRepository;

  private final CautionRepository cautionRepository;

  private final IngredientRepository ingredientRepository;

  private final RecipeMapper recipeMapper;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public List<RecipeResponseDto> getAllRecipes() {
    return recipeRepository.findAll().stream()
      .map(recipe -> conversionService.convert(recipe, RecipeResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public RecipeResponseDto getRecipeById(Long id) {
    return conversionService.convert(
      recipeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Constants.recipeNotFoundMessageFormat.formatted(id))),
      RecipeResponseDto.class);
  }

  @Override
  @Transactional
  public RecipeResponseDto createRecipe(CreateRecipeRequestDto requestDto) {
    val recipe = recipeRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, Recipe.class)));

    return conversionService.convert(recipe, RecipeResponseDto.class);
  }

  @Override
  @Transactional
  public RecipeResponseDto updateRecipeById(Long id, UpdateRecipeRequestDto requestDto) {
    val recipe = recipeRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(Constants.recipeNotFoundMessageFormat.formatted(id)));

    val updatedRecipe = recipeRepository.save(recipeMapper.updateRecipeWithUpdateRecipeRequestDto(recipe, requestDto));

    return conversionService.convert(updatedRecipe, RecipeResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteRecipeById(Long id) {
    val recipe = recipeRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(Constants.recipeNotFoundMessageFormat.formatted(id)));

    recipeRepository.delete(recipe);
  }

  @Override
  @Transactional
  public List<DietLabelResponseDto> getAllDietLabelsByRecipeId(Long id) {
    return dietLabelRepository.findAllByRecipeId(id).stream()
      .map(dietLabel -> conversionService.convert(dietLabel, DietLabelResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public List<HealthLabelResponseDto> getAllHealthLabelsByRecipeId(Long id) {
    return healthLabelRepository.findAllByRecipeId(id).stream()
      .map(healthLabel -> conversionService.convert(healthLabel, HealthLabelResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public List<CautionResponseDto> getAllCautionsByRecipeId(Long id) {
    return cautionRepository.findAllByRecipeId(id).stream()
      .map(caution -> conversionService.convert(caution, CautionResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public List<IngredientResponseDto> getAllIngredientsByRecipeId(Long id) {
    return ingredientRepository.findAllByRecipeId(id).stream()
      .map(ingredient -> conversionService.convert(ingredient, IngredientResponseDto.class))
      .toList();
  }

  @UtilityClass
  public static class Constants {

    public final String recipeNotFoundMessageFormat = "Couldn't find recipe with id %d.";

  }

}
