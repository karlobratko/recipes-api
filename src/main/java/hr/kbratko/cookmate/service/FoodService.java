package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.CreateFoodRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateFoodRequestDto;
import hr.kbratko.cookmate.dto.response.FoodCategoryResponseDto;
import hr.kbratko.cookmate.dto.response.FoodResponseDto;
import java.util.List;

public interface FoodService {

  List<FoodResponseDto> getAllFoods();

  FoodResponseDto getFoodById(Long id);

  FoodResponseDto createFood(CreateFoodRequestDto requestDto);

  FoodResponseDto updateFoodById(Long id, UpdateFoodRequestDto requestDto);

  void deleteFoodById(Long id);

  List<FoodCategoryResponseDto> getAllFoodCategories();

}
