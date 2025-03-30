package org.flooc.combo.dataoperation.query;

import java.util.List;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;

/**
 * 分页结果
 *
 * @param <T> 数据类型
 * @author sujie
 * @since 1.0.0
 */
@Getter
@Builder
public class PageResult<T> {

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


  public static <T> PageResult<T> of(List<T> list, Long total, Integer totalPages,
      DataPageable pageable) {
    return PageResult.<T>builder()
        .elements(list)
        .total(total)
        .totalPages(totalPages)
        .pageSize(pageable.getPageSize())
        .pageNumber(pageable.getPageIndex() + 1)
        .build();
  }

  public static <V, R> PageResult<R> of(DataPage<V> voPage, Function<V, R> function) {
    return PageResult.of(voPage.getElements().stream().map(function).toList(), voPage.getTotal(),
        voPage.totalPages(), voPage.getPageable());
  }

}