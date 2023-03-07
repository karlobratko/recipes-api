package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.CreateHealthLabelRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateHealthLabelRequestDto;
import hr.kbratko.cookmate.dto.response.HealthLabelResponseDto;
import java.util.List;

public interface HealthLabelService {

  List<HealthLabelResponseDto> getAllHealthLabels();

  HealthLabelResponseDto getHealthLabelById(Long id);

  HealthLabelResponseDto createHealthLabel(CreateHealthLabelRequestDto requestDto);

  HealthLabelResponseDto updateHealthLabelById(Long id, UpdateHealthLabelRequestDto requestDto);

  void deleteHealthLabelById(Long id);

}
