package hr.kbratko.cookmate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
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
@Table(name = Ingredient.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
@FieldNameConstants
public class Ingredient extends Auditable<Long> {

  @ManyToOne(fetch = FetchType.EAGER)
  private @NonNull Food food;

  @Column(nullable = false)
  private @NonNull BigDecimal quantity;

  @Column(length = Constants.measureColumnLength, nullable = false)
  private String measure;

  @ManyToOne
  private Recipe recipe;

  @lombok.Builder(builderClassName = "Builder")
  public Ingredient(Long id, UUID uuid, LocalDate createdDate, LocalDate lastModifiedDate, @NonNull Food food, @NonNull BigDecimal quantity, String measure, Recipe recipe) {
    super(id, uuid, createdDate, lastModifiedDate);
    this.food = food;
    this.quantity = quantity;
    this.measure = Objects.requireNonNullElse(measure, "<unit>");
    this.recipe = recipe;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String tableName = "ingredients";

    public static final String joinColumnName = "ingredient_id";

    public static final int measureColumnLength = 50;

  }

}
