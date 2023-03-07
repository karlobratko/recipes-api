package hr.kbratko.cookmate.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
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
@Table(name = Recipe.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
@FieldNameConstants
public class Recipe extends Auditable<Long> {

  @Column(length = Constants.titleColumnLength, nullable = false)
  private @NonNull String title;

  @Column(length = Constants.sourceColumnLength, nullable = false)
  private @NonNull String source;

  @Column(length = Constants.urlColumnLength, nullable = false)
  private @NonNull String url;

  @Column(nullable = false)
  private @NonNull BigDecimal calories;

  @Column(nullable = false)
  private @NonNull BigDecimal prepTime;

  @ManyToMany
  @JoinTable(
    name = Constants.dietLabelJoinTableName,
    joinColumns = @JoinColumn(name = Constants.joinColumnName),
    inverseJoinColumns = @JoinColumn(name = DietLabel.Constants.joinColumnName)
  )
  @ToString.Exclude
  private Set<DietLabel> dietLabels;

  @ManyToMany
  @JoinTable(
    name = Constants.healthLabelJoinTableName,
    joinColumns = @JoinColumn(name = Constants.joinColumnName),
    inverseJoinColumns = @JoinColumn(name = HealthLabel.Constants.joinColumnName)
  )
  @ToString.Exclude
  private Set<HealthLabel> healthLabels;

  @ManyToMany
  @JoinTable(
    name = Constants.cautionJoinTableName,
    joinColumns = @JoinColumn(name = Constants.joinColumnName),
    inverseJoinColumns = @JoinColumn(name = Caution.Constants.joinColumnName)
  )
  @ToString.Exclude
  private Set<Caution> cautions;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = Ingredient.Fields.recipe)
  @ToString.Exclude
  private Set<Ingredient> ingredients;

  @lombok.Builder(builderClassName = "Builder")
  public Recipe(Long id, UUID uuid, LocalDate createdDate, LocalDate lastModifiedDate, @NotNull String title, @NonNull String source, @NonNull String url, @NonNull BigDecimal calories, @NonNull BigDecimal prepTime, Set<DietLabel> dietLabels, Set<HealthLabel> healthLabels, Set<Caution> cautions, Set<Ingredient> ingredients) {
    super(id, uuid, createdDate, lastModifiedDate);
    this.title = title;
    this.source = source;
    this.url = url;
    this.calories = calories;
    this.prepTime = prepTime;
    this.dietLabels = Objects.requireNonNullElse(dietLabels, new HashSet<>());
    this.healthLabels = Objects.requireNonNullElse(healthLabels, new HashSet<>());
    this.cautions = Objects.requireNonNullElse(cautions, new HashSet<>());
    this.ingredients = Objects.requireNonNullElse(ingredients, new HashSet<>());
  }

  @UtilityClass
  public static class Constants {

    public final String tableName = "recipes";

    public final String joinColumnName = "recipe_id";

    public final String dietLabelJoinTableName = tableName + "_" + DietLabel.Constants.tableName;

    public final String healthLabelJoinTableName = tableName + "_" + HealthLabel.Constants.tableName;

    public final String cautionJoinTableName = tableName + "_" + Caution.Constants.tableName;

    public final int sourceColumnLength = 100;

    public final int urlColumnLength = 2048;

    public final int titleColumnLength = 250;

  }

}
