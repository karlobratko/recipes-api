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
import hr.kbratko.cookmate.exception.InvalidXmlException;
import hr.kbratko.cookmate.exception.SchemaNotFoundException;
import hr.kbratko.cookmate.service.RecipeService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import com.thaiopensource.relaxng.jaxp.CompactSyntaxSchemaFactory;

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

  @PostMapping(Constants.createRecipeWithXsdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> createRecipeWithXsd(@RequestParam("file") MultipartFile file) {
    try {
      if (file.isEmpty())
        throw new IllegalArgumentException("Multipart file should have content.");

      SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        .newSchema(new StreamSource(ResourceUtils.getFile("classpath:validation/xsd/recipe.xsd")))
        .newValidator()
        .validate(new StreamSource(file.getInputStream()));
    } catch (SAXException e) {
      throw new InvalidXmlException(e.getMessage());
    } catch (IOException e) {
      throw new SchemaNotFoundException(e.getMessage());
    }

    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(recipeService.createRecipeWithXml(file))
      );
  }

  @PostMapping(value = Constants.createRecipeWithRngPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> createRecipeWithRng(@RequestParam("file") MultipartFile file) {
    try {
      if (file.isEmpty())
        throw new IllegalArgumentException("Multipart file should have content.");

      System.setProperty(SchemaFactory.class.getName() + ":" + XMLConstants.RELAXNG_NS_URI, "com.thaiopensource.relaxng.jaxp.XMLSyntaxSchemaFactory");
      SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI)
        .newSchema(new StreamSource(ResourceUtils.getFile("classpath:validation/rng/recipe.rng")))
        .newValidator()
        .validate(new StreamSource(file.getInputStream()));
    } catch (SAXException e) {
      throw new InvalidXmlException(e.getMessage());
    } catch (IOException e) {
      throw new SchemaNotFoundException(e.getMessage());
    }

    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(recipeService.createRecipeWithXml(file))
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

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String requestMapping = ApplicationConstants.apiRecipeManagementRequestMapping + "/recipes";

    public static final String getAllRecipesGetMapping = "";

    public static final String getRecipeByIdGetMapping = "/{id}";

    public static final String createRecipePostMapping = "";

    public static final String createRecipeWithXsdPostMapping = "/xsd";

    public static final String createRecipeWithRngPostMapping = "/rng";

    public static final String updateRecipeByIdPostMapping = "/{id}";

    public static final String deleteRecipeByIdDeleteMapping = "/{id}";

    public static final String getAllHealthLabelsByRecipeIdGetMapping = "/{id}/health-labels";

    public static final String getAllDietLabelsByRecipeIdGetMapping = "/{id}/diet-labels";

    public static final String getAllCautionsByRecipeIdGetMapping = "/{id}/cautions";

    public static final String getAllIngredientsByRecipeIdGetMapping = "/{id}/ingredients";

  }

}
