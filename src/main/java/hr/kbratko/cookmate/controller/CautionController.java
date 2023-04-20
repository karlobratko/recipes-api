package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.CreateCautionRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateCautionRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.CautionResponseDto;
import hr.kbratko.cookmate.service.CautionService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CautionController.Constants.requestMapping)
@RequiredArgsConstructor
public class CautionController {

  private final CautionService cautionService;

  @GetMapping(Constants.getAllCautionsGetMapping)
  public ResponseEntity<ApiResponse<List<CautionResponseDto>>> getAllCautions() {
    return ResponseEntity.ok(
      ApiResponse.ok(cautionService.getAllCautions())
    );
  }

  @GetMapping(Constants.getCautionByIdGetMapping)
  public ResponseEntity<ApiResponse<CautionResponseDto>> getCautionById(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(cautionService.getCautionById(id))
    );
  }

  @PostMapping(Constants.createCautionPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<CautionResponseDto>> createCaution(@Valid @RequestBody CreateCautionRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(cautionService.createCaution(requestDto))
      );
  }

  @PostMapping(Constants.updateCautionByIdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<CautionResponseDto>> updateCautionById(@PathVariable Long id, @Valid @RequestBody UpdateCautionRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(cautionService.updateCautionById(id, requestDto))
    );
  }

  @DeleteMapping(Constants.deleteCautionByIdDeleteMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> deleteCautionById(@PathVariable Long id) {
    cautionService.deleteCautionById(id);
    return ResponseEntity.noContent().build();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String requestMapping = ApplicationConstants.apiRecipeManagementRequestMapping + "/cautions";

    public static final String getAllCautionsGetMapping = "";

    public static final String getCautionByIdGetMapping = "/{id}";

    public static final String createCautionPostMapping = "";

    public static final String updateCautionByIdPostMapping = "/{id}";

    public static final String deleteCautionByIdDeleteMapping = "/{id}";

  }

}
