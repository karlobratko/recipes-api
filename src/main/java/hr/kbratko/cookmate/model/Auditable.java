package hr.kbratko.cookmate.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, callSuper = true)
public abstract class Auditable<ID extends Number> extends Identifiable<ID> {

  @CreatedDate
  @Temporal(TemporalType.DATE)
  protected LocalDate createdDate;

  @LastModifiedDate
  @Temporal(TemporalType.DATE)
  protected LocalDate lastModifiedDate;

  public Auditable(ID id, UUID uuid, LocalDate createdDate, LocalDate lastModifiedDate) {
    super(id, uuid);
    this.createdDate = createdDate;
    this.lastModifiedDate = lastModifiedDate;
  }

}
