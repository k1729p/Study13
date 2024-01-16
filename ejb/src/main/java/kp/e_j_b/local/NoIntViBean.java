package kp.e_j_b.local;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * The stateless session bean.
 * <p>
 * This is <b>local</b>, no-interface view enterprise bean.
 * <p>
 * Because this enterprise bean class does not implement a business
 * interface,<BR>
 * the enterprise bean exposes a <b>local</b>, no-interface view.
 * <p>
 * (A business interface is not required if the enterprise bean exposes a
 * <b>local</b>, no-interface view.)
 */
@Stateless
public class NoIntViBean {

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	/**
	 * The constructor.
	 */
	public NoIntViBean() {
		super();
	}

	/**
	 * Checks the bean implementation.
	 * <p>
	 * Business method.
	 * 
	 * @param stamp the stamp
	 * @return message the message
	 */
	public String check(String stamp) {

		final String message = String.format("implementation[%s], stamp[%s], no-interface view stateless bean",
				this.getClass().getSimpleName(), stamp);
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("check(): %s", message));
		}
		return message;
	}
}