package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.CreateIngredientRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateIngredientRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.IngredientResponseDto;
import hr.kbratko.cookmate.service.IngredientService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
@RequestMapping(IngredientController.Constants.requestMapping)
@RequiredArgsConstructor
public class IngredientController {

  private final IngredientService ingredientService;

  @GetMapping(Constants.getAllIngredientsGetMapping)
  public ResponseEntity<ApiResponse<List<IngredientResponseDto>>> getAllIngredients() {
    return ResponseEntity.ok(
      ApiResponse.ok(ingredientService.getAllIngredients())
    );
  }

  @GetMapping(Constants.getIngredientByIdGetMapping)
  public ResponseEntity<ApiResponse<IngredientResponseDto>> getIngredientById(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(ingredientService.getIngredientById(id))
    );
  }

  @PostMapping(Constants.createIngredientPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<IngredientResponseDto>> createIngredient(@Valid @RequestBody CreateIngredientRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(ingredientService.createIngredient(requestDto))
      );
  }

  @PostMapping(Constants.updateIngredientByIdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<IngredientResponseDto>> updateIngredientById(@PathVariable Long id, @Valid @RequestBody UpdateIngredientRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(ingredientService.updateIngredientById(id, requestDto))
    );
  }

  @DeleteMapping(Constants.deleteIngredientByIdDeleteMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> deleteIngredientById(@PathVariable Long id) {
    ingredientService.deleteIngredientById(id);
    return ResponseEntity.noContent().build();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String requestMapping = ApplicationConstants.apiRecipeManagementRequestMapping + "/ingredients";

    public static final String getAllIngredientsGetMapping = "";

    public static final String getIngredientByIdGetMapping = "/{id}";

    public static final String createIngredientPostMapping = "";

    public static final String updateIngredientByIdPostMapping = "/{id}";

    public static final String deleteIngredientByIdDeleteMapping = "/{id}";

  }

}
