package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.CreateDietLabelRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateDietLabelRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.DietLabelResponseDto;
import hr.kbratko.cookmate.service.DietLabelService;
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
@RequestMapping(DietLabelController.Constants.requestMapping)
@RequiredArgsConstructor
public class DietLabelController {

  private final DietLabelService dietLabelService;

  @GetMapping(Constants.getAllDietLabelsGetMapping)
  public ResponseEntity<ApiResponse<List<DietLabelResponseDto>>> getAllDietLabels() {
    return ResponseEntity.ok(
      ApiResponse.ok(dietLabelService.getAllDietLabels())
    );
  }

  @GetMapping(Constants.getDietLabelByIdGetMapping)
  public ResponseEntity<ApiResponse<DietLabelResponseDto>> getDietLabelById(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(dietLabelService.getDietLabelById(id))
    );
  }

  @PostMapping(Constants.createDietLabelPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<DietLabelResponseDto>> createDietLabel(@Valid @RequestBody CreateDietLabelRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(dietLabelService.createDietLabel(requestDto))
      );
  }

  @PostMapping(Constants.updateDietLabelByIdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<DietLabelResponseDto>> updateDietLabelById(@PathVariable Long id, @Valid @RequestBody UpdateDietLabelRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(dietLabelService.updateDietLabelById(id, requestDto))
    );
  }

  @DeleteMapping(Constants.deleteDietLabelByIdDeleteMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> deleteDietLabelById(@PathVariable Long id) {
    dietLabelService.deleteDietLabelById(id);
    return ResponseEntity.noContent().build();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String requestMapping = ApplicationConstants.apiRecipeManagementRequestMapping + "/diet-labels";

    public static final String getAllDietLabelsGetMapping = "";

    public static final String getDietLabelByIdGetMapping = "/{id}";

    public static final String createDietLabelPostMapping = "";

    public static final String updateDietLabelByIdPostMapping = "/{id}";

    public static final String deleteDietLabelByIdDeleteMapping = "/{id}";

  }

}
