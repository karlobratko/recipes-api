package hr.kbratko.cookmate.repository;

import hr.kbratko.cookmate.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
