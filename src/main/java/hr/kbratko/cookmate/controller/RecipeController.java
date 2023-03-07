package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.CreateRecipeRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateRecipeRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.CautionResponseDto;
import hr.kbratko.cookmate.dto.response.DietLabelResponseDto;
import hr.kbratko.cookmate.dto.response.HealthLabelResponseDto;
import hr.kbratko.cookmate.dto.response.IngredientResponseDto;
import hr.kbratko.cookmate.dto.response.RecipeResponseDto;
import hr.kbratko.cookmate.service.RecipeService;
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
@RequestMapping(RecipeController.Constants.requestMapping)
@RequiredArgsConstructor
public class RecipeController {

  private final RecipeService recipeService;

  @GetMapping(Constants.getAllRecipesGetMapping)
  public ResponseEntity<ApiResponse<List<RecipeResponseDto>>> getAllRecipes() {
    return ResponseEntity.ok(
      ApiResponse.ok(recipeService.getAllRecipes())
    );
  }

  @GetMapping(Constants.getRecipeByIdGetMapping)
  public ResponseEntity<ApiResponse<RecipeResponseDto>> getRecipeById(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(recipeService.getRecipeById(id))
    );
  }

  @PostMapping(Constants.createRecipePostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<RecipeResponseDto>> createRecipe(@Valid @RequestBody CreateRecipeRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(recipeService.createRecipe(requestDto))
      );
  }

  @PostMapping(Constants.updateRecipeByIdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<RecipeResponseDto>> updateRecipeById(@PathVariable Long id, @Valid @RequestBody UpdateRecipeRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(recipeService.updateRecipeById(id, requestDto))
    );
  }

  @DeleteMapping(Constants.deleteRecipeByIdDeleteMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> deleteRecipeById(@PathVariable Long id) {
    recipeService.deleteRecipeById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(Constants.getAllDietLabelsByRecipeIdGetMapping)
  public ResponseEntity<ApiResponse<List<DietLabelResponseDto>>> getAllDietLabelsByRecipeId(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(recipeService.getAllDietLabelsByRecipeId(id))
    );
  }

  @GetMapping(Constants.getAllHealthLabelsByRecipeIdGetMapping)
  public ResponseEntity<ApiResponse<List<HealthLabelResponseDto>>> getAllHealthLabelsByRecipeId(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(recipeService.getAllHealthLabelsByRecipeId(id))
    );
  }

  @GetMapping(Constants.getAllCautionsByRecipeIdGetMapping)
  public ResponseEntity<ApiResponse<List<CautionResponseDto>>> getAllCautionsByRecipeId(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(recipeService.getAllCautionsByRecipeId(id))
    );
  }

  @GetMapping(Constants.getAllIngredientsByRecipeIdGetMapping)
  public ResponseEntity<ApiResponse<List<IngredientResponseDto>>> getAllIngredientsByRecipeId(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(recipeService.getAllIngredientsByRecipeId(id))
    );
  }

  @UtilityClass
  public static class Constants {

    public final String requestMapping = ApplicationConstants.apiRecipeManagementRequestMapping + "/recipes";

    public final String getAllRecipesGetMapping = "";

    public final String getRecipeByIdGetMapping = "/{id}";

    public final String createRecipePostMapping = "";

    public final String updateRecipeByIdPostMapping = "/{id}";

    public final String deleteRecipeByIdDeleteMapping = "/{id}";

    public final String getAllHealthLabelsByRecipeIdGetMapping = "/{id}/health-labels";

    public final String getAllDietLabelsByRecipeIdGetMapping = "/{id}/diet-labels";

    public final String getAllCautionsByRecipeIdGetMapping = "/{id}/cautions";

    public final String getAllIngredientsByRecipeIdGetMapping = "/{id}/ingredients";

  }

}
