package hr.kbratko.cookmate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = Privilege.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
@FieldNameConstants
public class Privilege extends Identifiable<Long> {

  @Column(length = 50, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private String name;

  @ManyToMany(mappedBy = Role.Fields.privileges)
  @ToString.Exclude
  private Set<Role> roles;

  @lombok.Builder(builderClassName = "Builder")
  public Privilege(Long id, UUID uuid, String name, Set<Role> roles) {
    super(id, uuid);
    this.name = name;
    this.roles = roles;
  }

  @UtilityClass
  public static class Constants {

    public final String tableName = "privileges";

    public final String joinColumnName = "privilege_id";

  }

}
