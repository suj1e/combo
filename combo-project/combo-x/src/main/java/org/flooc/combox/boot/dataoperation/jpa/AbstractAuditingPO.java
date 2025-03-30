package org.flooc.combox.boot.dataoperation.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.dataoperation.constant.DataCommonFields;
import org.flooc.combo.dataoperation.enums.DataState;
import org.flooc.combo.dataoperation.support.DataStateConverter;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {DataCommonFields.CREATED_BY, DataCommonFields.CREATED_DATE,
    DataCommonFields.LAST_MODIFIED_BY, DataCommonFields.LAST_MODIFIED_DATE,
    DataCommonFields.VERSION},
    allowGetters = true)
@Getter
@Setter
@SoftDelete
public abstract class AbstractAuditingPO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  public String id;

  @CreatedBy
  @Column(name = "created_by", nullable = false, length = 50, updatable = false)
  private String createdBy;

  @CreatedDate
  @Column(name = "created_date", updatable = false)
  private Instant createdDate = Instant.now();

  @LastModifiedBy
  @Column(name = "last_modified_by", length = 50)
  private String lastModifiedBy;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private Instant lastModifiedDate = Instant.now();

  @Version
  @Column(name = "version")
  private Integer version;

  @Convert(converter = DataStateConverter.class)
  private DataState dataState;

  @PrePersist
  public void prePersist() {
    this.setId(DataIdUtils.nextId());
    this.setDataState(DataState.VALID);
  }

}