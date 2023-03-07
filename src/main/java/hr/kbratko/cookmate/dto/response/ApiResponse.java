package hr.kbratko.cookmate.dto.response;

public record ApiResponse<T>(T data, String error) {

  public static <T> ApiResponse<T> of(T data, String error) {
    return new ApiResponse<>(data, error);
  }

  public static <T> ApiResponse<T> ok(T data) {
    return new ApiResponse<>(data, null);
  }

  public static ApiResponse<?> error(String error) {
    return new ApiResponse<>(null, error);
  }

}
