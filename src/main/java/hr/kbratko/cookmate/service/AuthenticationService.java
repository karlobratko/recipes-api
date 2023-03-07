package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.LoginRequestDto;
import hr.kbratko.cookmate.dto.request.RegisterRequestDto;
import hr.kbratko.cookmate.dto.response.AuthenticationResponseDto;

public interface AuthenticationService {

  AuthenticationResponseDto register(RegisterRequestDto requestDto);

  AuthenticationResponseDto login(LoginRequestDto requestDto);

}
