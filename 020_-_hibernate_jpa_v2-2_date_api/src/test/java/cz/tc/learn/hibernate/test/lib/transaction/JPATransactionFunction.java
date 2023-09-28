package cz.tc.learn.hibernate.test.lib.transaction;

import java.util.function.Function;
import javax.persistence.EntityManager;

/**
 * @author Vlad Mihalcea
 */
@FunctionalInterface
public interface JPATransactionFunction<T> extends Function<EntityManager, T> {
	default void beforeTransactionCompletion() {

	}

	default void afterTransactionCompletion() {

	}
}
