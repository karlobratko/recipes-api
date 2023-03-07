package hr.kbratko.cookmate.runner;

import hr.kbratko.cookmate.model.Caution;
import hr.kbratko.cookmate.model.DietLabel;
import hr.kbratko.cookmate.model.Food;
import hr.kbratko.cookmate.model.FoodCategory;
import hr.kbratko.cookmate.model.HealthLabel;
import hr.kbratko.cookmate.model.Ingredient;
import hr.kbratko.cookmate.model.Privilege;
import hr.kbratko.cookmate.model.Recipe;
import hr.kbratko.cookmate.model.Role;
import hr.kbratko.cookmate.model.User;
import hr.kbratko.cookmate.model.codebook.Privileges;
import hr.kbratko.cookmate.model.codebook.Roles;
import hr.kbratko.cookmate.repository.CautionRepository;
import hr.kbratko.cookmate.repository.DietLabelRepository;
import hr.kbratko.cookmate.repository.FoodRepository;
import hr.kbratko.cookmate.repository.HealthLabelRepository;
import hr.kbratko.cookmate.repository.IngredientRepository;
import hr.kbratko.cookmate.repository.PrivilegeRepository;
import hr.kbratko.cookmate.repository.RecipeRepository;
import hr.kbratko.cookmate.repository.RoleRepository;
import hr.kbratko.cookmate.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapRunner implements CommandLineRunner {

  private final PasswordEncoder passwordEncoder;

  private final PrivilegeRepository privilegeRepository;

  private final RoleRepository roleRepository;

  private final UserRepository userRepository;

  private final DietLabelRepository dietLabelRepository;

  private final HealthLabelRepository healthLabelRepository;

  private final CautionRepository cautionRepository;

  private final FoodRepository foodRepository;

  private final IngredientRepository ingredientRepository;

  private final RecipeRepository recipeRepository;

  @Override
  public void run(String... args) {
    val privilege1 = Privilege.builder().name(Privileges.CREATE.name()).build();
    val privilege2 = Privilege.builder().name(Privileges.READ.name()).build();
    val privilege3 = Privilege.builder().name(Privileges.UPDATE.name()).build();
    val privilege4 = Privilege.builder().name(Privileges.DELETE.name()).build();

    privilegeRepository.saveAll(List.of(privilege1, privilege2, privilege3, privilege4));

    val role1 = Role.builder().name(Roles.ADMIN.name()).privileges(Set.of(privilege1, privilege2, privilege3, privilege4)).build();
    val role2 = Role.builder().name(Roles.USER.name()).privileges(Set.of(privilege2)).build();

    roleRepository.saveAll(List.of(role1, role2));

    val user1 = User.builder()
      .firstName("Karlo")
      .lastName("Bratko")
      .email("kbratko@racunarstvo.hr")
      .username("kbratko")
      .password(passwordEncoder.encode("Pa$$w0rd"))
      .enabled(true)
      .roles(Set.of(role1))
      .build();

    val user2 = User.builder()
      .firstName("Lucian")
      .lastName("Bratko")
      .email("lbratkok@racunarstvo.hr")
      .username("lbratko")
      .password(passwordEncoder.encode("Pa$$w0rd"))
      .enabled(true)
      .roles(Set.of(role2))
      .build();

    userRepository.saveAll(List.of(user1, user2));

    val dietLabel1 = DietLabel.builder().name("Balanced").build();

    dietLabelRepository.saveAll(List.of(dietLabel1));

    val healthLabel1 = HealthLabel.builder().name("Vegetarian").build();
    val healthLabel2 = HealthLabel.builder().name("Pescatarian").build();
    val healthLabel3 = HealthLabel.builder().name("Egg-Free").build();
    val healthLabel4 = HealthLabel.builder().name("Peanut-Free").build();
    val healthLabel5 = HealthLabel.builder().name("Tree-Nut-Free").build();
    val healthLabel6 = HealthLabel.builder().name("Soy-Free").build();
    val healthLabel7 = HealthLabel.builder().name("Fish-Free").build();
    val healthLabel8 = HealthLabel.builder().name("Shellfish-Free").build();
    val healthLabel9 = HealthLabel.builder().name("Pork-Free").build();
    val healthLabel10 = HealthLabel.builder().name("Red-Meat-Free").build();
    val healthLabel11 = HealthLabel.builder().name("Crustacean-Free").build();
    val healthLabel12 = HealthLabel.builder().name("Celery-Free").build();
    val healthLabel13 = HealthLabel.builder().name("Mustard-Free").build();
    val healthLabel14 = HealthLabel.builder().name("Sesame-Free").build();
    val healthLabel15 = HealthLabel.builder().name("Lupine-Free").build();
    val healthLabel16 = HealthLabel.builder().name("Mollusk-Free").build();
    val healthLabel17 = HealthLabel.builder().name("Alcohol-Free").build();
    val healthLabel18 = HealthLabel.builder().name("Sulfite-Free").build();
    val healthLabel19 = HealthLabel.builder().name("Kosher").build();

    healthLabelRepository.saveAll(
      List.of(
        healthLabel1, healthLabel2, healthLabel3, healthLabel4, healthLabel5,
        healthLabel6, healthLabel7, healthLabel8, healthLabel9, healthLabel10,
        healthLabel11, healthLabel12, healthLabel13, healthLabel14, healthLabel15,
        healthLabel16, healthLabel17, healthLabel18, healthLabel19
      )
    );

    val caution1 = Caution.builder().name("Sulfites").build();

    cautionRepository.saveAll(List.of(caution1));

    val food1 = Food.builder().name("unsalted butter").category(FoodCategory.DAIRY).build();
    val food2 = Food.builder().name("flour").category(FoodCategory.GRAINS).build();
    val food3 = Food.builder().name("milk").category(FoodCategory.DAIRY).build();
    val food4 = Food.builder().name("bay leaf").category(FoodCategory.VEGETABLES).build();
    val food5 = Food.builder().name("spinach").category(FoodCategory.VEGETABLES).build();
    val food6 = Food.builder().name("ricotta cheese").category(FoodCategory.DAIRY).build();
    val food7 = Food.builder().name("nutmeg").category(FoodCategory.VEGETABLES).build();
    val food8 = Food.builder().name("lasagne sheets").category(FoodCategory.GRAINS).build();
    val food9 = Food.builder().name("parmesan cheese").category(FoodCategory.DAIRY).build();

    foodRepository.saveAll(
      List.of(
        food1, food2, food3, food4, food5, food6, food7, food8, food9
      )
    );

    val recipe1 = Recipe.builder()
      .title("Spinach lasagne")
      .source("Jamie Oliver")
      .url("http://www.jamieoliver.com/recipes/pasta-recipes/spinach-lasagne/")
      .dietLabels(Set.of(dietLabel1))
      .healthLabels(
        Set.of(
          healthLabel1, healthLabel2, healthLabel3, healthLabel4, healthLabel5,
          healthLabel6, healthLabel7, healthLabel8, healthLabel9, healthLabel10,
          healthLabel11, healthLabel12, healthLabel13, healthLabel14, healthLabel15,
          healthLabel16, healthLabel17, healthLabel18, healthLabel19
        )
      )
      .cautions(Set.of(caution1))
      .calories(BigDecimal.valueOf(2987.5654))
      .prepTime(BigDecimal.valueOf(50.0))
      .build();

    recipeRepository.saveAll(List.of(recipe1));

    val ingredient1 = Ingredient.builder().food(food1).quantity(BigDecimal.valueOf(70.0)).measure("gram").recipe(recipe1).build();
    val ingredient2 = Ingredient.builder().food(food2).quantity(BigDecimal.valueOf(50.0)).measure("gram").recipe(recipe1).build();
    val ingredient3 = Ingredient.builder().food(food3).quantity(BigDecimal.valueOf(800.0)).measure("milliliter").recipe(recipe1).build();
    val ingredient4 = Ingredient.builder().food(food4).quantity(BigDecimal.valueOf(1.0)).recipe(recipe1).build();
    val ingredient5 = Ingredient.builder().food(food5).quantity(BigDecimal.valueOf(800.0)).measure("gram").recipe(recipe1).build();
    val ingredient6 = Ingredient.builder().food(food6).quantity(BigDecimal.valueOf(200.0)).measure("gram").recipe(recipe1).build();
    val ingredient7 = Ingredient.builder().food(food7).quantity(BigDecimal.valueOf(1.0)).recipe(recipe1).build();
    val ingredient8 = Ingredient.builder().food(food8).quantity(BigDecimal.valueOf(300.0)).measure("gram").recipe(recipe1).build();
    val ingredient9 = Ingredient.builder().food(food9).quantity(BigDecimal.valueOf(100.0)).measure("gram").recipe(recipe1).build();

    ingredientRepository.saveAll(
      List.of(
        ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
        ingredient6, ingredient7, ingredient8, ingredient9
      )
    );
  }

}
