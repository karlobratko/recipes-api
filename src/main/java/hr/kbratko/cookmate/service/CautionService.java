package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.CreateCautionRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateCautionRequestDto;
import hr.kbratko.cookmate.dto.response.CautionResponseDto;
import java.util.List;

public interface CautionService {

  List<CautionResponseDto> getAllCautions();

  CautionResponseDto getCautionById(Long id);

  CautionResponseDto createCaution(CreateCautionRequestDto requestDto);

  CautionResponseDto updateCautionById(Long id, UpdateCautionRequestDto requestDto);

  void deleteCautionById(Long id);

}
