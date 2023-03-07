package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.CreateFoodRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateFoodRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.FoodCategoryResponseDto;
import hr.kbratko.cookmate.dto.response.FoodResponseDto;
import hr.kbratko.cookmate.service.FoodService;
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
@RequestMapping(FoodController.Constants.requestMapping)
@RequiredArgsConstructor
public class FoodController {

  private final FoodService foodService;

  @GetMapping(Constants.getAllFoodsGetMapping)
  public ResponseEntity<ApiResponse<List<FoodResponseDto>>> getAllFoods() {
    return ResponseEntity.ok(
      ApiResponse.ok(foodService.getAllFoods())
    );
  }

  @GetMapping(Constants.getFoodByIdGetMapping)
  public ResponseEntity<ApiResponse<FoodResponseDto>> getFoodById(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(foodService.getFoodById(id))
    );
  }

  @PostMapping(Constants.createFoodPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<FoodResponseDto>> createFood(@Valid @RequestBody CreateFoodRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(foodService.createFood(requestDto))
      );
  }

  @PostMapping(Constants.updateFoodByIdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<FoodResponseDto>> updateFoodById(@PathVariable Long id, @Valid @RequestBody UpdateFoodRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(foodService.updateFoodById(id, requestDto))
    );
  }

  @DeleteMapping(Constants.deleteFoodByIdDeleteMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> deleteFoodById(@PathVariable Long id) {
    foodService.deleteFoodById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(Constants.getAllFoodCategoriesGetMapping)
  public ResponseEntity<ApiResponse<List<FoodCategoryResponseDto>>> getAllFoodCategories() {
    return ResponseEntity.ok(
      ApiResponse.ok(foodService.getAllFoodCategories())
    );
  }

  @UtilityClass
  public static class Constants {

    public final String requestMapping = ApplicationConstants.apiRecipeManagementRequestMapping + "/foods";

    public final String getAllFoodsGetMapping = "";

    public final String getFoodByIdGetMapping = "/{id}";

    public final String createFoodPostMapping = "";

    public final String updateFoodByIdPostMapping = "/{id}";

    public final String deleteFoodByIdDeleteMapping = "/{id}";

    public final String getAllFoodCategoriesGetMapping = "/categories";

  }

}
