package hr.kbratko.cookmate.repository;

import hr.kbratko.cookmate.model.HealthLabel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthLabelRepository extends JpaRepository<HealthLabel, Long> {

  @Query(
    value = "SELECT hl " +
      "FROM HealthLabel hl " +
      "INNER JOIN Recipe r " +
      "WHERE r.id = :recipeId " +
      "ORDER BY hl.id ASC"
  )
  List<HealthLabel> findAllByRecipeId(Long recipeId);

}
