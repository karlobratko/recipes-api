package hr.kbratko.cookmate.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstants {

  public static final String apiRequestMapping = "/api/v1";

  public static final String apiUserManagementRequestMapping = apiRequestMapping + "/user-management";

  public static final String apiRecipeManagementRequestMapping = apiRequestMapping + "/recipe-management";

}
