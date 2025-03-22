package org.flooc.combo.x.data.common.support;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.x.data.common.constant.ValidStatus;

@Getter
@Setter
public abstract class AbstractEntity implements Entity<String>, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public String id;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;

	private Integer version;

	private ValidStatus validStatus;

	public void invalid() {
		this.setValidStatus(ValidStatus.INVALID);
	}

	public void valid() {
		this.setValidStatus(ValidStatus.VALID);
	}

	public void init() {
	}

}