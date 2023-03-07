package hr.kbratko.cookmate.repository;

import hr.kbratko.cookmate.model.Caution;
import hr.kbratko.cookmate.model.Ingredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

  @Query(
    value = "SELECT i " +
      "FROM Ingredient i " +
      "INNER JOIN Recipe r " +
      "WHERE r.id = :recipeId " +
      "ORDER BY i.id ASC"
  )
  List<Ingredient> findAllByRecipeId(Long recipeId);

}
