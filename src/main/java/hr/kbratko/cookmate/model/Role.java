package hr.kbratko.cookmate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@Table(name = Role.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
@FieldNameConstants
public class Role extends Identifiable<Long> {

  @Column(length = 50, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private String name;

  @ManyToMany(mappedBy = User.Fields.roles)
  @ToString.Exclude
  private Set<User> users;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = Role.Constants.privilegeJoinTableName,
    joinColumns = @JoinColumn(name = Role.Constants.joinColumnName),
    inverseJoinColumns = @JoinColumn(name = Privilege.Constants.joinColumnName)
  )
  @ToString.Exclude
  private Set<Privilege> privileges;

  @lombok.Builder(builderClassName = "Builder")
  public Role(Long id, UUID uuid, String name, Set<User> users, Set<Privilege> privileges) {
    super(id, uuid);
    this.name = name;
    this.users = users;
    this.privileges = privileges;
  }

  @UtilityClass
  public static class Constants {

    public final String tableName = "roles";

    public final String joinColumnName = "role_id";

    public final String privilegeJoinTableName = tableName + "_" + Privilege.Constants.tableName;

  }

}
