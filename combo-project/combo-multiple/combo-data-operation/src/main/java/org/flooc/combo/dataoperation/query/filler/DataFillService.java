package org.flooc.combo.dataoperation.query.filler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
public interface DataFillService {

	<T> void fill(Class<T> config, List<T> dataList);

	<T> void registerConfig(Class<T> config);

	@SuppressWarnings("unchecked")
	default <T> void fill(T data) {
		Optional.ofNullable(data).ifPresent(i -> fill((Class<T>) i.getClass(), Collections.singletonList(i)));
	}

	@SuppressWarnings("unchecked")
	default <T> void fill(List<T> dataList) {
		if (CollectionUtils.isEmpty(dataList)) {
			return;
		}
		if (dataList.size() == 1) {
			fill(dataList.getFirst());
		}
		else {
			fill((Class<T>) dataList.getFirst(), dataList);
		}
	}

}
