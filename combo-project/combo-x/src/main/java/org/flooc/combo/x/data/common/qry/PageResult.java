package org.flooc.combo.x.data.common.qry;

import java.util.List;
import java.util.function.Function;
import lombok.Data;

/**
 * 分页结果
 *
 * @param <T> 数据类型
 */
@Data
public class PageResult<T> {

	public PageResult() {
	}

	/**
	 * 数据总量
	 */
	private Long total;

	/**
	 * 总页数
	 */
	private Integer totalPages;

	/**
	 * 页数
	 */
	private Integer pageSize;

	/**
	 * 页码
	 */
	private Integer pageNumber;

	/**
	 * 数据
	 */
	private List<T> elements;

	public PageResult(List<T> elements, Long total, Integer totalPages, Integer pageSize, Integer pageNumber) {
		this.elements = elements;
		this.total = total;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}

	public static <T> PageResult<T> of(List<T> list, Long total, Integer totalPages, DataPageable pageable) {
		return new PageResult<>(list, total, totalPages, pageable.getPageSize(), pageable.getPageIndex() + 1);
	}

	public static <V, R> PageResult<R> of(DataPage<V> voPage, Function<V, R> function) {
		return PageResult.of(voPage.getElements().stream().map(function).toList(), voPage.getTotal(),
				voPage.totalPages(), voPage.getPageable());
	}

}