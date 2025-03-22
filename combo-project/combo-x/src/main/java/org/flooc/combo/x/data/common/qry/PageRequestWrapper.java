package org.flooc.combo.x.data.common.qry;

import java.util.Map;
import lombok.Data;

/**
 * 分页请求包装
 *
 * @param <T> 数据类型
 */
@Data
public class PageRequestWrapper<T> {

	/**
	 * 请求数据
	 */
	private T requestData;

	/**
	 * 页数
	 */
	private Integer pageSize;

	/**
	 * 页码
	 */
	private Integer pageNumber;

	/**
	 * 排序
	 */
	private Map<String, String> sorts;

}
