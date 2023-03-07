package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.CreateIngredientRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateIngredientRequestDto;
import hr.kbratko.cookmate.dto.response.IngredientResponseDto;
import java.util.List;

public interface IngredientService {

  List<IngredientResponseDto> getAllIngredients();

  IngredientResponseDto getIngredientById(Long id);

  IngredientResponseDto createIngredient(CreateIngredientRequestDto requestDto);

  IngredientResponseDto updateIngredientById(Long id, UpdateIngredientRequestDto requestDto);

  void deleteIngredientById(Long id);

}
