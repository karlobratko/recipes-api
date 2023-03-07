package hr.kbratko.cookmate.service.impl;

import hr.kbratko.cookmate.dto.request.CreateUserRequestDto;
import hr.kbratko.cookmate.dto.request.UpdateUserRequestDto;
import hr.kbratko.cookmate.dto.response.RoleResponseDto;
import hr.kbratko.cookmate.dto.response.UserResponseDto;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.mapper.UserMapper;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.repository.RoleRepository;
import hr.kbratko.cookmate.repository.UserRepository;
import hr.kbratko.cookmate.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final UserMapper userMapper;

  private final ConversionService conversionService;

  @Override
  @Transactional
  public List<UserResponseDto> getAllUsers() {
    return userRepository.findAll().stream()
      .map(user -> conversionService.convert(user, UserResponseDto.class))
      .toList();
  }

  @Override
  @Transactional
  public UserResponseDto getUserById(Long id) {
    return conversionService.convert(
      userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(UserServiceImpl.Constants.userNotFoundMessageFormat.formatted(id))),
      UserResponseDto.class
    );
  }

  @Override
  @Transactional
  public UserResponseDto createUser(CreateUserRequestDto requestDto) {
    val user = userRepository.save(Objects.requireNonNull(conversionService.convert(requestDto, User.class)));

    return conversionService.convert(user, UserResponseDto.class);
  }

  @Override
  @Transactional
  public UserResponseDto updateUserById(Long id, UpdateUserRequestDto requestDto) {
    val user = userRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(UserServiceImpl.Constants.userNotFoundMessageFormat.formatted(id)));

    val updatedUser = userRepository.save(userMapper.updateUserWithUpdateUserRequestDto(user, requestDto));

    return conversionService.convert(user, UserResponseDto.class);
  }

  @Override
  @Transactional
  public void deleteUserById(Long id) {
    val user = userRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(UserServiceImpl.Constants.userNotFoundMessageFormat.formatted(id)));

    userRepository.delete(user);
  }

  @Override
  public List<RoleResponseDto> getAllRoles() {
    return roleRepository.findAll().stream()
      .map(role -> conversionService.convert(role, RoleResponseDto.class))
      .toList();
  }

  @UtilityClass
  public static class Constants {

    public final String userNotFoundMessageFormat = "Couldn't find user with id %d.";

  }

}
