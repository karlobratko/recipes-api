package hr.kbratko.cookmate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
@Table(name = HealthLabel.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
@FieldNameConstants
public class HealthLabel extends Auditable<Long> {

  @Column(length = 100, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private @NonNull String name;

  @ManyToMany(mappedBy = Recipe.Fields.healthLabels)
  @ToString.Exclude
  private Set<Recipe> recipes;

  @lombok.Builder(builderClassName = "Builder")
  public HealthLabel(Long id, UUID uuid, LocalDate createdDate, LocalDate lastModifiedDate, @NonNull String name, Set<Recipe> recipes) {
    super(id, uuid, createdDate, lastModifiedDate);
    this.name = name;
    this.recipes = Objects.requireNonNullElse(recipes, new HashSet<>());
  }

  @UtilityClass
  public static class Constants {

    public final String tableName = "health_labels";

    public final String joinColumnName = "health_label_id";

  }

}
