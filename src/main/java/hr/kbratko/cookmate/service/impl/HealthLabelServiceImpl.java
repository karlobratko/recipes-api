package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.CreateHealthLabelRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateHealthLabelRequestDto;
import hr.kbratko.cookmate.dto.response.HealthLabelResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.mapper.HealthLabelMapper;
import hr.kbratko.cookmate.model.HealthLabel;
import hr.kbratko.cookmate.repository.HealthLabelRepository;
import hr.kbratko.cookmate.service.HealthLabelService;
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
public class HealthLabelServiceImpl implements HealthLabelService {

  private final HealthLabelRepository healthLabelRepository;

  private final HealthLabelMapper healthLabelMapper;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public List<HealthLabelResponseDto> getAllHealthLabels() {
    return healthLabelRepository.findAll().stream()
      .map(healthLabel -> conversionService.convert(healthLabel, HealthLabelResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public HealthLabelResponseDto getHealthLabelById(Long id) {
    return conversionService.convert(
      healthLabelRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Constants.healthLabelNotFoundMessageFormat.formatted(id))),
      HealthLabelResponseDto.class
    );
  }

  @Override
  @Transactional
  public HealthLabelResponseDto createHealthLabel(CreateHealthLabelRequestDto requestDto) {
    val healthLabel = healthLabelRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, HealthLabel.class)));

    return conversionService.convert(healthLabel, HealthLabelResponseDto.class);
  }

  @Override
  @Transactional
  public HealthLabelResponseDto updateHealthLabelById(Long id, UpdateHealthLabelRequestDto requestDto) {
    val healthLabel = healthLabelRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(Constants.healthLabelNotFoundMessageFormat.formatted(id)));

    val updatedHealthLabel = healthLabelRepository.save(healthLabelMapper.updateHealthLabelWithUpdateHealthLabelRequestDto(healthLabel, requestDto));

    return conversionService.convert(updatedHealthLabel, HealthLabelResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteHealthLabelById(Long id) {
    val healthLabel = healthLabelRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(Constants.healthLabelNotFoundMessageFormat.formatted(id)));

    healthLabelRepository.delete(healthLabel);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String healthLabelNotFoundMessageFormat = "Couldn't find health label with id %d.";

  }

}
