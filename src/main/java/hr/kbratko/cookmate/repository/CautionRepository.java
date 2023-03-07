package hr.kbratko.cookmate.repository;

import hr.kbratko.cookmate.model.Caution;
import hr.kbratko.cookmate.model.HealthLabel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CautionRepository extends JpaRepository<Caution, Long> {

  @Query(
    value = "SELECT c " +
      "FROM Caution c " +
      "INNER JOIN Recipe r " +
      "WHERE r.id = :recipeId " +
      "ORDER BY c.id ASC"
  )
  List<Caution> findAllByRecipeId(Long recipeId);

}
