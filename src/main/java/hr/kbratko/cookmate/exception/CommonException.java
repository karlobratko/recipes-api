package hr.kbratko.cookmate.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class CommonException extends RuntimeException {

  CommonException(final String message) {
    super(message);
  }

  CommonException(final String message, final Throwable cause) {
    super(message, cause);
  }

  CommonException(final Throwable cause) {
    super(cause);
  }
  
}
