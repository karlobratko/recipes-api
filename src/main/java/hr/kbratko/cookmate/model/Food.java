package hr.kbratko.cookmate.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = Food.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
@FieldNameConstants
public class Food extends Auditable<Long> {

  @Column(length = 100, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private @NonNull String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private @NonNull FoodCategory category;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = Ingredient.Fields.food)
  @ToString.Exclude
  private Set<Ingredient> ingredients;

  @lombok.Builder(builderClassName = "Builder")
  public Food(Long id, UUID uuid, LocalDate createdDate, LocalDate lastModifiedDate, @NonNull String name, @NonNull FoodCategory category, Set<Ingredient> ingredients) {
    super(id, uuid, createdDate, lastModifiedDate);
    this.name = name;
    this.category = category;
    this.ingredients = Objects.requireNonNullElse(ingredients, new HashSet<>());
  }

  @UtilityClass
  public static class Constants {

    public final String tableName = "foods";

    public final String joinColumnName = "food_id";

  }

}
