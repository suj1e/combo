package org.flooc.combo.x.data.common.qry;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

@Getter
public final class DataPage<T> {

	/**
	 * 数据
	 */
	private final List<T> elements;

	/**
	 * 分页信息
	 */
	private final DataPageable pageable;

	/**
	 * 总的数据数量
	 */
	private final long total;

	private DataPage(List<T> elements, DataPageable pageable, long total) {
		this.elements = elements;
		this.pageable = pageable;
		this.total = total;
	}

	public static <T> DataPage<T> of(List<T> elements, DataPageable pageable, long total) {
		return new DataPage<>(elements, pageable, total);
	}

	public static <E, V> DataPage<V> of(DataPage<E> ePage, Function<E, V> function) {
		return DataPage.of(ePage.getElements().stream().map(function).toList(), ePage.getPageable(), ePage.getTotal());
	}

	public static DataPage<?> empty(DataPageable pageable) {
		return new DataPage<>(Collections.emptyList(), pageable, 0);
	}

	/**
	 * 当前页号
	 */
	public int currentPage() {
		return this.pageable.getPageIndex();
	}

	/**
	 * 每页大小
	 */
	public int pageSize() {
		return pageable.getPageSize();
	}

	/**
	 * 总页数
	 */
	public int totalPages() {
		return pageSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) pageSize());
	}

	/**
	 * 是否有数据
	 */
	public boolean hasContent() {
		return !CollectionUtils.isEmpty(this.elements);
	}

	/**
	 * 是否为第一页
	 */
	public boolean isFirst() {
		return !hasPrevious();
	}

	/**
	 * 是否为最后一页
	 */
	public boolean isLast() {
		return !hasNext();
	}

	/**
	 * 是否有下一页
	 */
	public boolean hasNext() {
		return currentPage() + 1 < totalPages();
	}

	/**
	 * 是否有上一页
	 */
	public boolean hasPrevious() {
		return currentPage() > 0;
	}

	public <R> DataPage<R> apply(Function<T, R> converter) {
		if (CollectionUtils.isEmpty(getElements())) {
			return new DataPage<>(Collections.emptyList(), getPageable(), getTotal());
		}

		List<R> results = elements.stream()
			.filter(Objects::nonNull)
			.map(converter)
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
		return new DataPage<>(results, getPageable(), getTotal());
	}

}
