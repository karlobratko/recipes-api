package hr.kbratko.cookmate.advice;

import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.exception.CommonException;
import hr.kbratko.cookmate.exception.ConversionException;
import hr.kbratko.cookmate.exception.DatabaseEnumException;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.exception.UnauthorizedException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.val;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionAdvice {

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<ApiResponse<?>> handleNotFoundException(CommonException e) {
    return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
    IllegalArgumentException.class,
    NoSuchElementException.class,
    BadCredentialsException.class,
    CommonException.class,
    HttpMessageNotReadableException.class
  })
  public ResponseEntity<ApiResponse<?>> handleBadRequestException(RuntimeException e) {
    return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({UnauthorizedException.class})
  public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(CommonException e) {
    return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({
    ConversionException.class,
    ConverterNotFoundException.class,
    DatabaseEnumException.class
  })
  public ResponseEntity<ApiResponse<?>> handleInternalServerErrorException(CommonException e) {
    return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException e) {
    val errors = e.getBindingResult().getAllErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.joining("\n"));
    return new ResponseEntity<>(ApiResponse.error(errors), HttpStatus.BAD_REQUEST);
  }

}
