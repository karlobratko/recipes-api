package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.CreateFoodRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateFoodRequestDto;
import hr.kbratko.cookmate.dto.response.FoodCategoryResponseDto;
import hr.kbratko.cookmate.dto.response.FoodResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.mapper.FoodMapper;
import hr.kbratko.cookmate.model.Food;
import hr.kbratko.cookmate.model.FoodCategory;
import hr.kbratko.cookmate.repository.FoodRepository;
import hr.kbratko.cookmate.service.FoodService;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

  private final FoodRepository foodRepository;

  private final FoodMapper foodMapper;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public List<FoodResponseDto> getAllFoods() {
    return foodRepository.findAll().stream()
      .map(food -> conversionService.convert(food, FoodResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public FoodResponseDto getFoodById(Long id) {
    return conversionService.convert(
      foodRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(FoodServiceImpl.Constants.foodNotFoundMessageFormat.formatted(id))),
      FoodResponseDto.class
    );
  }

  @Override
  @Transactional
  public FoodResponseDto createFood(CreateFoodRequestDto requestDto) {
    val food = foodRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, Food.class)));

    return conversionService.convert(food, FoodResponseDto.class);
  }

  @Override
  @Transactional
  public FoodResponseDto updateFoodById(Long id, UpdateFoodRequestDto requestDto) {
    val food = foodRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(FoodServiceImpl.Constants.foodNotFoundMessageFormat.formatted(id)));

    val updatedFood = foodRepository.save(foodMapper.updateFoodWithUpdateFoodRequestDto(food, requestDto));

    return conversionService.convert(updatedFood, FoodResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteFoodById(Long id) {
    val food = foodRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(FoodServiceImpl.Constants.foodNotFoundMessageFormat.formatted(id)));

    foodRepository.delete(food);
  }

  @Override
  public List<FoodCategoryResponseDto> getAllFoodCategories() {
    return Arrays.stream(FoodCategory.values())
      .map(category -> conversionService.convert(category, FoodCategoryResponseDto.class))
      .toList();
  }

  @UtilityClass
  public static class Constants {

    public final String foodNotFoundMessageFormat = "Couldn't find food with id %d.";

  }

}
