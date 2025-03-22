package org.flooc.combo.x.data.common.operation;

import org.flooc.combo.x.data.common.repo.CmdRepo;

/**
 * @author sujie
 * @since 1.0.0
 */
public final class EOperations {

	private EOperations() {
	}

	public static <T, I> ECreator<T, I> doCreate(CmdRepo<T, I> cmdRepo) {
		return new ECreator<>(cmdRepo);
	}

	public static <T, I> EUpdater<T, I> doUpdate(CmdRepo<T, I> cmdRepo) {
		return new EUpdater<>(cmdRepo);
	}

	public static <T, I> EDeleter<T, I> doDelete(CmdRepo<T, I> cmdRepo) {
		return new EDeleter<>(cmdRepo);
	}

}
