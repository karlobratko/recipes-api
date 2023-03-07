package hr.kbratko.cookmate.exception;

public class ConversionException extends CommonException {

  public ConversionException(final String message) {
    super(message);
  }

  public ConversionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ConversionException(final Throwable cause) {
    super(cause);
  }

}

