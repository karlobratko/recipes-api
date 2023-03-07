package hr.kbratko.cookmate.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {

  public final String apiRequestMapping = "/api/v1";

  public final String apiUserManagementRequestMapping = apiRequestMapping + "/user-management";

  public final String apiRecipeManagementRequestMapping = apiRequestMapping + "/recipe-management";

}
