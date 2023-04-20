package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.CreateDietLabelRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateDietLabelRequestDto;
import hr.kbratko.cookmate.dto.response.DietLabelResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.mapper.DietLabelMapper;
import hr.kbratko.cookmate.model.DietLabel;
import hr.kbratko.cookmate.service.DietLabelService;
import hr.kbratko.cookmate.repository.DietLabelRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DietLabelServiceImpl implements DietLabelService {

  private final DietLabelRepository dietLabelRepository;

  private final DietLabelMapper dietLabelMapper;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public List<DietLabelResponseDto> getAllDietLabels() {
    return dietLabelRepository.findAll().stream()
      .map(dietLabel -> conversionService.convert(dietLabel, DietLabelResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public DietLabelResponseDto getDietLabelById(Long id) {
    return conversionService.convert(
      dietLabelRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Constants.dietLabelNotFoundMessageFormat.formatted(id))),
      DietLabelResponseDto.class
    );
  }

  @Override
  @Transactional
  public DietLabelResponseDto createDietLabel(CreateDietLabelRequestDto requestDto) {
    val dietLabel = dietLabelRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, DietLabel.class)));

    return conversionService.convert(dietLabel, DietLabelResponseDto.class);
  }

  @Override
  @Transactional
  public DietLabelResponseDto updateDietLabelById(Long id, UpdateDietLabelRequestDto requestDto) {
    val dietLabel = dietLabelRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(Constants.dietLabelNotFoundMessageFormat.formatted(id)));

    val updatedDietLabel = dietLabelRepository.save(dietLabelMapper.updateDietLabelWithUpdateDietLabelRequestDto(dietLabel, requestDto));

    return conversionService.convert(updatedDietLabel, DietLabelResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteDietLabelById(Long id) {
    val dietLabel = dietLabelRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(Constants.dietLabelNotFoundMessageFormat.formatted(id)));

    dietLabelRepository.delete(dietLabel);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String dietLabelNotFoundMessageFormat = "Couldn't find diet label with id %d.";

  }

}
