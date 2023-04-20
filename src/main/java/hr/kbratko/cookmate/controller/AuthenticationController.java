package hr.kbratko.cookmate.controller;

import hr.kbratko.cookmate.constants.ApplicationConstants;
import hr.kbratko.cookmate.dto.request.LoginRequestDto;
import hr.kbratko.cookmate.dto.request.RegisterRequestDto;
import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.dto.response.AuthenticationResponseDto;
import hr.kbratko.cookmate.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthenticationController.Constants.requestMapping)
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping(Constants.registerPostMapping)
  public ResponseEntity<ApiResponse<AuthenticationResponseDto>> register(@Valid @RequestBody RegisterRequestDto requestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        ApiResponse.ok(
          authenticationService.register(requestDto)
        )
      );
  }

  @PostMapping(Constants.loginPostMapping)
  public ResponseEntity<ApiResponse<AuthenticationResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {
    return ResponseEntity.ok(
      ApiResponse.ok(
        authenticationService.login(requestDto)
      )
    );
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String requestMapping = ApplicationConstants.apiUserManagementRequestMapping + "/auth";

    public static final String registerPostMapping = "/register";

    public static final String loginPostMapping = "/login";

  }

}
