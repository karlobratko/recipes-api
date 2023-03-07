package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.CreateHealthLabelRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateHealthLabelRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.HealthLabelResponseDto;
import hr.kbratko.cookmate.service.HealthLabelService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
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
@RequestMapping(HealthLabelController.Constants.requestMapping)
@RequiredArgsConstructor
public class HealthLabelController {

  private final HealthLabelService healthLabelService;

  @GetMapping(Constants.getAllHealthLabelsGetMapping)
  public ResponseEntity<ApiResponse<List<HealthLabelResponseDto>>> getAllHealthLabels() {
    return ResponseEntity.ok(
      ApiResponse.ok(healthLabelService.getAllHealthLabels())
    );
  }

  @GetMapping(Constants.getHealthLabelByIdGetMapping)
  public ResponseEntity<ApiResponse<HealthLabelResponseDto>> getHealthLabelById(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(healthLabelService.getHealthLabelById(id))
    );
  }

  @PostMapping(Constants.createHealthLabelPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<HealthLabelResponseDto>> createHealthLabel(@Valid @RequestBody CreateHealthLabelRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(healthLabelService.createHealthLabel(requestDto))
      );
  }

  @PostMapping(Constants.updateHealthLabelByIdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<HealthLabelResponseDto>> updateHealthLabelById(@PathVariable Long id, @Valid @RequestBody UpdateHealthLabelRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(healthLabelService.updateHealthLabelById(id, requestDto))
    );
  }

  @DeleteMapping(Constants.deleteHealthLabelByIdDeleteMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> deleteHealthLabelById(@PathVariable Long id) {
    healthLabelService.deleteHealthLabelById(id);
    return ResponseEntity.noContent().build();
  }

  @UtilityClass
  public static class Constants {

    public final String requestMapping = ApplicationConstants.apiRecipeManagementRequestMapping + "/health-labels";

    public final String getAllHealthLabelsGetMapping = "";

    public final String getHealthLabelByIdGetMapping = "/{id}";

    public final String createHealthLabelPostMapping = "";

    public final String updateHealthLabelByIdPostMapping = "/{id}";

    public final String deleteHealthLabelByIdDeleteMapping = "/{id}";

  }

}
