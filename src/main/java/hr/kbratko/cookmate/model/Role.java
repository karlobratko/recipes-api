package hr.kbratko.cookmate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

@Entity
@Table(name = Role.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
public class Role extends Identifiable<Long> {

  @Column(length = 50, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private String name;

  @ManyToMany(mappedBy = "roles")
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

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String tableName = "roles";

    public static final String joinColumnName = "role_id";

    public static final String privilegeJoinTableName = tableName + "_" + Privilege.Constants.tableName;

  }

}
