package kp.e_j_b;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import kp.util.Tools;

/**
 * The abstract implementation of the {@link Common} interface.
 *
 */
public abstract class CommonImpl implements Common {

	private int previous = 0;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	/**
	 * The constructor.
	 */
	protected CommonImpl() {
		super();
	}

	/**
	 * {@inheritDoc} Implemented.
	 * 
	 */
	@Override
	public String check(String stamp) {

		final String message = String.format("implementation[%s], stamp[%s]", this.getClass().getSimpleName(), stamp);
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("check(): %s", message));
		}
		return message;
	}

	/**
	 * {@inheritDoc} Implemented.
	 * 
	 */
	@Override
	public String change(String stamp, int number) {

		final String message = String.format(
				"stamp[%s], current number[%s], previous number[%s], current equals previous[%b], object hash code[%s]",
				stamp, number, previous, number == previous, Tools.hashCodeFormatted(this));
		previous = number;
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("change():%n\t%s", message));
		}
		return message;
	}
}