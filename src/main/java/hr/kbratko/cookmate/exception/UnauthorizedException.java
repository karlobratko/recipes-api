package hr.kbratko.cookmate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends CommonException {

  public UnauthorizedException() {
    super("Only admin may perform this action.");
  }

  public UnauthorizedException(final String message) {
    super(message);
  }

  public UnauthorizedException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public UnauthorizedException(final Throwable cause) {
    super(cause);
  }

}

