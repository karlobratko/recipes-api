package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.CreateRecipeRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateRecipeRequestDto;
import hr.kbratko.cookmate.dto.response.CautionResponseDto;
import hr.kbratko.cookmate.dto.response.DietLabelResponseDto;
import hr.kbratko.cookmate.dto.response.HealthLabelResponseDto;
import hr.kbratko.cookmate.dto.response.IngredientResponseDto;
import hr.kbratko.cookmate.dto.response.RecipeResponseDto;
import hr.kbratko.cookmate.model.Caution;
import java.util.List;

public interface RecipeService {

  List<RecipeResponseDto> getAllRecipes();

  RecipeResponseDto getRecipeById(Long id);

  RecipeResponseDto createRecipe(CreateRecipeRequestDto requestDto);

  RecipeResponseDto updateRecipeById(Long id, UpdateRecipeRequestDto requestDto);

  void deleteRecipeById(Long id);

  List<HealthLabelResponseDto> getAllHealthLabelsByRecipeId(Long id);

  List<DietLabelResponseDto> getAllDietLabelsByRecipeId(Long id);

  List<CautionResponseDto> getAllCautionsByRecipeId(Long id);

  List<IngredientResponseDto> getAllIngredientsByRecipeId(Long id);

}
