package hr.kbratko.cookmate.advice;

import hr.kbratko.cookmate.dto.response.ApiResponse;
import hr.kbratko.cookmate.exception.CommonException;
import hr.kbratko.cookmate.exception.ConversionException;
import hr.kbratko.cookmate.exception.DatabaseEnumException;
import hr.kbratko.cookmate.exception.InvalidXmlException;
import hr.kbratko.cookmate.exception.NotFoundException;
import hr.kbratko.cookmate.exception.UnauthorizedException;
import hr.kbratko.cookmate.exception.SchemaNotFoundException;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class ApplicationExceptionAdvice {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException e) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<ApiResponse<?>> handleNotFoundException(CommonException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
  }

  @ExceptionHandler({
    IllegalArgumentException.class,
    NoSuchElementException.class,
    BadCredentialsException.class,
    CommonException.class,
    HttpMessageNotReadableException.class,
    InvalidXmlException.class
  })
  public ResponseEntity<ApiResponse<?>> handleBadRequestException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
  }

  @ExceptionHandler({UnauthorizedException.class})
  public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(CommonException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(e.getMessage()));
  }

  @ExceptionHandler({
    ConversionException.class,
    ConverterNotFoundException.class,
    DatabaseEnumException.class,
    SchemaNotFoundException.class
  })
  public ResponseEntity<ApiResponse<?>> handleInternalServerErrorException(CommonException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(e.getMessage()));
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException e) {
    val errors = e.getBindingResult().getAllErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.joining("\n"));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(errors));
  }

}
