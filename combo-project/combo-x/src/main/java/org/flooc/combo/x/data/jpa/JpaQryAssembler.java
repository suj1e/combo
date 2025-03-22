package org.flooc.combo.x.data.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import org.flooc.combo.x.data.common.constant.DataCommonField;
import org.flooc.combo.x.data.common.qry.DataPage;
import org.flooc.combo.x.data.common.qry.DataPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.CollectionUtils;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class JpaQryAssembler {

	private JpaQryAssembler() {
	}

	public static Optional<Sort> sort(Map<String, String> sorts) {
		if (CollectionUtils.isEmpty(sorts)) {
			return Optional.empty();
		}
		List<Order> orders = new ArrayList<>();
		for (Entry<String, String> entry : sorts.entrySet()) {
			if (Direction.ASC == Direction.fromString(entry.getKey())) {
				orders.add(Order.asc(entry.getValue()));
			}
			else {
				orders.add(Order.desc(entry.getValue()));
			}
		}
		return Optional.of(Sort.by(orders));
	}

	public static Sort defaultSort(Map<String, String> sorts) {
		Sort sort = Sort.by(Direction.DESC, DataCommonField.CREATED_DATE);
		sort = sort(sorts).map(sort::and).orElse(sort);
		return sort;
	}

	public static PageRequest defaultPageRequest(DataPageable pageable) {
		return PageRequest.of(pageable.getPageIndex(), pageable.getPageSize(),
				JpaQryAssembler.defaultSort(pageable.getSorts()));
	}

	public static <E, O> DataPage<E> ofE(Page<O> doPage, Function<O, E> function) {
		return DataPage.of(doPage.getContent().stream().map(function).toList(),
				DataPageable.of(doPage.getNumber(), doPage.getSize()), doPage.getTotalElements());
	}

}
