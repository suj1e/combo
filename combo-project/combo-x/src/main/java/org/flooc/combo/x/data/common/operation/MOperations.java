package org.flooc.combo.x.data.common.operation;


import org.flooc.combo.x.web.startup.SpringAppUtil;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class MOperations {

	private MOperations() {
	}

	public static <M> MCreator<M> doCreateBy(Class<? extends MProcessor<M>> modelProcessorType) {
		return new MCreator<>(SpringAppUtil.getBean(modelProcessorType));
	}

	public static <M> MCreator<M> doCreate(Class<M> type) {
		return new MCreator<>();
	}

}
