package org.flooc.combo.dataoperation.query.filler;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.flooc.combo.common.util.SpringAppUtil;

/**
 * @author sujie
 * @since 1.0.0
 */
@Slf4j
public final class DataFiller {

	private DataFiller() {
	}

	private static volatile DataFillService DATA_FILL_SERVICE;

	public static <T> List<T> fill(Class<T> config, List<T> dataList) {
		init();
		DATA_FILL_SERVICE.fill(config, dataList);
		return dataList;
	}

	public static <T> void registerConfig(Class<T> config) {
		init();
		DATA_FILL_SERVICE.registerConfig(config);
	}

	public static <T> T fill(T data) {
		init();
		DATA_FILL_SERVICE.fill(data);
		return data;
	}

	public static <T> List<T> fill(List<T> dataList) {
		init();
		DATA_FILL_SERVICE.fill(dataList);
		return dataList;
	}

	private static synchronized void init() {
		if (DATA_FILL_SERVICE == null) {
			DATA_FILL_SERVICE = SpringAppUtil.getBean(DataFillService.class);
		}
		if (log.isDebugEnabled()) {
			log.debug("DataFiller init success, service: {}", DATA_FILL_SERVICE);
		}
	}

}
