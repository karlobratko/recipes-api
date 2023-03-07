package hr.kbratko.cookmate.repository;

import hr.kbratko.cookmate.model.DietLabel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DietLabelRepository extends JpaRepository<DietLabel, Long> {

  @Query(
    value = "SELECT dl " +
      "FROM DietLabel dl " +
      "INNER JOIN Recipe r " +
      "WHERE r.id = :recipeId " +
      "ORDER BY dl.id ASC"
  )
  List<DietLabel> findAllByRecipeId(Long recipeId);

}
