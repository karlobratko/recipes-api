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
public class Privilege extends Identifiable<Long> {

  @Column(length = 50, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private String name;

  @ManyToMany(mappedBy = "privileges")
  @ToString.Exclude
  private Set<Role> roles;

  @lombok.Builder(builderClassName = "Builder")
  public Privilege(Long id, UUID uuid, String name, Set<Role> roles) {
    super(id, uuid);
    this.name = name;
    this.roles = roles;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String tableName = "privileges";

    public static final String joinColumnName = "privilege_id";

  }

}
