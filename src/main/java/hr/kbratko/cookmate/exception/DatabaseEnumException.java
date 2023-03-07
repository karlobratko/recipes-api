package hr.kbratko.cookmate.exception;

public class DatabaseEnumException extends CommonException {

  public DatabaseEnumException(final String message) {
    super(message);
  }

  public DatabaseEnumException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DatabaseEnumException(final Throwable cause) {
    super(cause);
  }

}