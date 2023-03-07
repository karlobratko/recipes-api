package hr.kbratko.cookmate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = User.Constants.tableName)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true, callSuper = true)
@ToString(doNotUseGetters = true)
@FieldNameConstants
public class User extends Auditable<Long> implements UserDetails {

  @Column(length = Constants.firstNameColumnLength, nullable = false)
  private @NonNull String firstName;

  @Column(length = Constants.lastNameColumnLength, nullable = false)
  private @NonNull String lastName;

  @Column(length = Constants.usernameColumnLength, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private @NonNull String username;

  @Column(length = Constants.emailColumnLength, nullable = false, unique = true)
  @EqualsAndHashCode.Include
  private @NonNull String email;

  @ToString.Exclude
  @Column(nullable = false)
  private @NonNull String password;

  @ToString.Exclude
  private Boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = User.Constants.roleJoinTableName,
    joinColumns = @JoinColumn(name = User.Constants.joinColumnName),
    inverseJoinColumns = @JoinColumn(name = Role.Constants.joinColumnName)
  )
  @ToString.Exclude
  private Set<Role> roles;

  @lombok.Builder(builderClassName = "Builder")
  public User(Long id, UUID uuid, LocalDate createdDate, LocalDate lastModifiedDate, @NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String email, @NonNull String password, Boolean enabled, Set<Role> roles) {
    super(id, uuid, createdDate, lastModifiedDate);
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.enabled = Objects.requireNonNullElse(enabled, false);
    this.roles = Objects.requireNonNullElse(roles, new HashSet<>());
  }

  public String getFullName() {
    return "%s %s".formatted(firstName, lastName);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<SimpleGrantedAuthority> authorities = roles.stream()
      .map(role -> new SimpleGrantedAuthority(role.getName()))
      .collect(Collectors.toSet());

    authorities.addAll(
      roles.stream()
        .flatMap(role -> role.getPrivileges().stream()
          .map(privilege -> new SimpleGrantedAuthority(privilege.getName())))
        .collect(Collectors.toSet())
    );
    
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @UtilityClass
  public static class Constants {

    public final String tableName = "users";

    public final String joinColumnName = "user_id";

    public final String roleJoinTableName = tableName + "_" + Role.Constants.tableName;

    public final int firstNameColumnLength = 50;

    public final int lastNameColumnLength = 50;

    public final int usernameColumnLength = 50;

    public final int emailColumnLength = 254;

  }

}
