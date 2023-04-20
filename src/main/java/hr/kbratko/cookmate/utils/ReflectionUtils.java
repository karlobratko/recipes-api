package hr.kbratko.cookmate.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtils {

  public static Map<String, Object> toMap(Object obj) {
    Map<String, Object> map = new HashMap<>();

    for (Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      try {
        map.put(field.getName(), field.get(obj));
      } catch (Exception ignored) {
      }
    }

    return map;
  }

}
