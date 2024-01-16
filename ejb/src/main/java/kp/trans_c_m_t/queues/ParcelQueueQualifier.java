package kp.trans_c_m_t.queues;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.inject.Qualifier;

/**
 * The parcel queue qualifier.
 *
 */
@Qualifier
@Documented
@Retention(RUNTIME)
@Target({ FIELD, TYPE })
public @interface ParcelQueueQualifier {
	/**
	 * Sets the type of the queue.
	 * 
	 * @return the type
	 */
	Type value();

	/**
	 * The type enumeration.
	 *
	 */
	enum Type {
		/**
		 * The 'create' type.
		 */
		CREATE,
		/**
		 * The 'confirm create' type.
		 */
		CONFIRM_CREATE,
		/**
		 * The 'read' type.
		 */
		READ
	}
}