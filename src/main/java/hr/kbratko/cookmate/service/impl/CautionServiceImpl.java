package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.CreateCautionRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateCautionRequestDto;
import hr.kbratko.cookmate.dto.response.CautionResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.mapper.CautionMapper;
import hr.kbratko.cookmate.model.Caution;
import hr.kbratko.cookmate.repository.CautionRepository;
import hr.kbratko.cookmate.service.CautionService;
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
public class CautionServiceImpl implements CautionService {

  private final CautionRepository cautionRepository;

  private final CautionMapper cautionMapper;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public List<CautionResponseDto> getAllCautions() {
    return cautionRepository.findAll().stream()
      .map(caution -> conversionService.convert(caution, CautionResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public CautionResponseDto getCautionById(Long id) {
    return conversionService.convert(
      cautionRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(CautionServiceImpl.Constants.cautionNotFoundMessageFormat.formatted(id))),
      CautionResponseDto.class
    );
  }

  @Override
  @Transactional
  public CautionResponseDto createCaution(CreateCautionRequestDto requestDto) {
    val caution = cautionRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, Caution.class)));

    return conversionService.convert(caution, CautionResponseDto.class);
  }

  @Override
  @Transactional
  public CautionResponseDto updateCautionById(Long id, UpdateCautionRequestDto requestDto) {
    val caution = cautionRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(CautionServiceImpl.Constants.cautionNotFoundMessageFormat.formatted(id)));

    val updatedCaution = cautionRepository.save(cautionMapper.updateCautionWithUpdateCautionRequestDto(caution, requestDto));

    return conversionService.convert(updatedCaution, CautionResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteCautionById(Long id) {
    val caution = cautionRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(CautionServiceImpl.Constants.cautionNotFoundMessageFormat.formatted(id)));

    cautionRepository.delete(caution);
  }

  @UtilityClass
  public static class Constants {

    public final String cautionNotFoundMessageFormat = "Couldn't find caution with id %d.";

  }

}
