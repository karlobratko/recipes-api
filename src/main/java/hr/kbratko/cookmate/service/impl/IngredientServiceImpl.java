package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.CreateIngredientRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateIngredientRequestDto;
import hr.kbratko.cookmate.dto.response.IngredientResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.mapper.IngredientMapper;
import hr.kbratko.cookmate.model.Ingredient;
import hr.kbratko.cookmate.repository.IngredientRepository;
import hr.kbratko.cookmate.service.IngredientService;
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
public class IngredientServiceImpl implements IngredientService {

  private final IngredientRepository ingredientRepository;

  private final IngredientMapper ingredientMapper;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public List<IngredientResponseDto> getAllIngredients() {
    return ingredientRepository.findAll().stream()
      .map(ingredient -> conversionService.convert(ingredient, IngredientResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public IngredientResponseDto getIngredientById(Long id) {
    return conversionService.convert(
      ingredientRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(IngredientServiceImpl.Constants.ingredientNotFoundMessageFormat.formatted(id))),
      IngredientResponseDto.class
    );
  }

  @Override
  @Transactional
  public IngredientResponseDto createIngredient(CreateIngredientRequestDto requestDto) {
    val ingredient = ingredientRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, Ingredient.class)));

    return conversionService.convert(ingredient, IngredientResponseDto.class);
  }

  @Override
  @Transactional
  public IngredientResponseDto updateIngredientById(Long id, UpdateIngredientRequestDto requestDto) {
    val ingredient = ingredientRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(IngredientServiceImpl.Constants.ingredientNotFoundMessageFormat.formatted(id)));

    val updatedIngredient = ingredientRepository.save(ingredientMapper.updateIngredientWithUpdateIngredientRequestDto(ingredient, requestDto));

    return conversionService.convert(ingredient, IngredientResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteIngredientById(Long id) {
    val ingredient = ingredientRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(IngredientServiceImpl.Constants.ingredientNotFoundMessageFormat.formatted(id)));

    ingredientRepository.delete(ingredient);
  }

  @UtilityClass
  public static class Constants {

    public final String ingredientNotFoundMessageFormat = "Couldn't find ingredient with id %d.";

  }

}
