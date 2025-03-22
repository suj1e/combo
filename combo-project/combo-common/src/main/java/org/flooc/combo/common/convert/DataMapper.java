package org.flooc.combo.common.convert;

/**
 * @param <O> 对象
 * @param <V> value
 * @author sujie
 * @since 1.0.0
 */
public interface DataMapper<O, V> {

  O val2Obj(V val);

  V obj2Val(O obj);

}
