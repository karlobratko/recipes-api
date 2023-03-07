package hr.kbratko.cookmate.service;

import hr.kbratko.cookmate.dto.request.CreateUserRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateUserRequestDto;
import hr.kbratko.cookmate.dto.response.RoleResponseDto;
import hr.kbratko.cookmate.dto.response.UserResponseDto;
import java.util.List;

public interface UserService {

  List<UserResponseDto> getAllUsers();

  UserResponseDto getUserById(Long id);

  UserResponseDto createUser(CreateUserRequestDto requestDto);

  UserResponseDto updateUserById(Long id, UpdateUserRequestDto requestDto);

  void deleteUserById(Long id);

  List<RoleResponseDto> getAllRoles();
}
