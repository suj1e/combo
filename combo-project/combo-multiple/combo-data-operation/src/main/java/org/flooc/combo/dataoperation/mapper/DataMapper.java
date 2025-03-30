package org.flooc.combo.dataoperation.mapper;

/**
 * 转换器
 *
 * @param <O> 对象
 * @param <V> 值
 * @author sujie
 * @since 1.0.0
 */
public interface DataMapper<O, V> {

  O val2Obj(V val);

  V obj2Val(O obj);

}
