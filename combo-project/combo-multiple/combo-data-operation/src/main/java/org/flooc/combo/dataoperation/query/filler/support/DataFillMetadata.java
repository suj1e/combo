package org.flooc.combo.dataoperation.query.filler.support;

import lombok.Getter;
import lombok.Setter;
import org.flooc.combo.dataoperation.query.filler.DataFill;

/**
 * @author sujie
 * @since 1.0.0
 */
@Setter
@Getter
public class DataFillMetadata {

	private String convert;

	private String load;

	private String relateKey;

	private String sourceKey;

	private int processLevel;

	public static DataFillMetadata fromAnnotation(DataFill dataFill) {
		DataFillMetadata metadata = new DataFillMetadata();
		metadata.setConvert(dataFill.convert());
		metadata.setLoad(dataFill.load());
		metadata.setProcessLevel(dataFill.processLevel());
		metadata.setRelateKey(dataFill.relateKey());
		metadata.setSourceKey(dataFill.sourceKey());
		return metadata;
	}

}
