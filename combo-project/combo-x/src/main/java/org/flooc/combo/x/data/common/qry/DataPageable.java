package org.flooc.combo.x.data.common.qry;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flooc.combo.x.data.common.qry.PageRequestWrapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataPageable {

	/**
	 * 页号
	 */
	private Integer pageIndex;

	/**
	 * 每页大小
	 */
	private Integer pageSize;

	private Map<String, String> sorts;

	public static DataPageable of(Integer pageIndex, Integer pageSize) {
		return DataPageable.builder().pageIndex(pageIndex).pageSize(pageSize).build();
	}

	public static DataPageable of(Integer pageIndex, Integer pageSize, Map<String, String> sorts) {
		return DataPageable.builder().pageIndex(pageIndex).pageSize(pageSize).sorts(sorts).build();
	}

	public static <Q> DataPageable of(PageRequestWrapper<Q> wrapper) {
		return DataPageable.of(wrapper.getPageNumber() - 1, wrapper.getPageSize(), wrapper.getSorts());
	}

	public Integer limit() {
		return pageSize;
	}

	/**
	 * 偏移起始量，从 0 开始
	 */
	public Integer offset() {
		if (pageIndex == null || pageSize == null) {
			return null;
		}
		return pageIndex * pageSize;
	}

}
