package org.flooc.combo.dataoperation.support;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.dataoperation.core.BizIdentity;
import org.flooc.combo.dataoperation.enums.DataState;

/**
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Setter
public abstract class AbstractEntity implements Entity<String>, Serializable, BizIdentity {

	@Serial
	private static final long serialVersionUID = 1L;

	public String id;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;

	private Integer version;

	private DataState dataState;

	public void invalid() {
		this.setDataState(DataState.INVALID);
	}

	public void valid() {
		this.setDataState(DataState.VALID);
	}

	public void init() {
	}


}