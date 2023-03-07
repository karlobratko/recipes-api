package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.CreateDietLabelRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateDietLabelRequestDto;
import hr.kbratko.cookmate.dto.response.DietLabelResponseDto;
import java.util.List;

public interface DietLabelService {

  List<DietLabelResponseDto> getAllDietLabels();

  DietLabelResponseDto getDietLabelById(Long id);

  DietLabelResponseDto createDietLabel(CreateDietLabelRequestDto requestDto);

  DietLabelResponseDto updateDietLabelById(Long id, UpdateDietLabelRequestDto requestDto);

  void deleteDietLabelById(Long id);

}
