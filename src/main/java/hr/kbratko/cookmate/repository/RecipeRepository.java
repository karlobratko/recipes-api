package hr.kbratko.cookmate.repository;

import hr.kbratko.cookmate.model.HealthLabel;
import hr.kbratko.cookmate.model.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
