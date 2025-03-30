package org.flooc.combo.dataoperation.core;

import org.flooc.combo.common.util.SpringAppUtil;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class ModelOperations {

	private ModelOperations() {
	}

	public static <M> ModelCreator<M> doCreateBy(Class<? extends ModelProcessor<M>> modelProcessorType) {
		return new ModelCreator<>(SpringAppUtil.getBean(modelProcessorType));
	}

	public static <M> ModelCreator<M> doCreate(Class<M> type) {
		return new ModelCreator<>();
	}

}
