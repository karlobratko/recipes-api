package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.CreateUserRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateUserRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.RoleResponseDto;
import hr.kbratko.cookmate.dto.response.UserResponseDto;
import hr.kbratko.cookmate.service.UserService;
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
@RequestMapping(UserController.Constants.requestMapping)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping(Constants.getAllUsersGetMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
    return ResponseEntity.ok(
      ApiResponse.ok(userService.getAllUsers())
    );
  }

  @GetMapping(Constants.getUserByIdGetMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(
      ApiResponse.ok(userService.getUserById(id))
    );
  }

  @PostMapping(Constants.createUserPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(userService.createUser(requestDto))
      );
  }

  @PostMapping(Constants.updateUserByIdPostMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUserById(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(userService.updateUserById(id, requestDto))
    );
  }

  @DeleteMapping(Constants.deleteUserByIdDeleteMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<?>> deleteUserById(@PathVariable Long id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(Constants.getAllRolesGetMapping)
  @PreAuthorize("hasAuthority(T(hr.kbratko.cookmate.model.codebook.Roles).ADMIN.name())")
  public ResponseEntity<ApiResponse<List<RoleResponseDto>>> getAllRoles() {
    return ResponseEntity.ok(
      ApiResponse.ok(userService.getAllRoles())
    );
  }

  @UtilityClass
  public static class Constants {

    public final String requestMapping = ApplicationConstants.apiUserManagementRequestMapping + "/users";

    public final String getAllUsersGetMapping = "";

    public final String getUserByIdGetMapping = "/{id}";

    public final String createUserPostMapping = "";

    public final String updateUserByIdPostMapping = "/{id}";

    public final String deleteUserByIdDeleteMapping = "/{id}";

    public final String getAllRolesGetMapping = "/roles";
  }

}
