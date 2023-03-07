package hr.kbratko.cookmate.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true)
public abstract class Identifiable<ID extends Number> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  protected ID id;

  @Column(nullable = false, unique = true)
  protected UUID uuid;

  @PrePersist
  private void prePersist() {
    this.uuid = UUID.randomUUID();
  }

}
