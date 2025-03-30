package org.flooc.combox.boot.dataoperation.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import org.flooc.combo.dataoperation.constant.DataCommonFields;
import org.flooc.combo.dataoperation.query.DataPage;
import org.flooc.combo.dataoperation.query.DataPageable;
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
public final class JpaQueryAssembler {

	private JpaQueryAssembler() {
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
		Sort sort = Sort.by(Direction.DESC, DataCommonFields.CREATED_DATE);
		sort = sort(sorts).map(sort::and).orElse(sort);
		return sort;
	}

	public static PageRequest defaultPageRequest(DataPageable pageable) {
		return PageRequest.of(pageable.getPageIndex(), pageable.getPageSize(),
				JpaQueryAssembler.defaultSort(pageable.getSorts()));
	}

	public static <E, O> DataPage<E> of(Page<O> poPage, Function<O, E> function) {
		return DataPage.of(poPage.getContent().stream().map(function).toList(),
				DataPageable.of(poPage.getNumber(), poPage.getSize()), poPage.getTotalElements());
	}

}
