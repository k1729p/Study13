package kp.e_j_b.interceptors;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

import kp.util.Tools;

/**
 * The time elapsed interceptor.
 * <p>
 * The interceptor functionality is defined in the Java Interceptors
 * specification.
 * <p>
 * The CDI enhances this functionality. But CDI-style interceptor is not
 * researched here because it needs enabling in the 'beans.xml'.
 */
public class TimeElapsedInterceptor {
	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	private static final int PAUSE = 1;

	/**
	 * The constructor.
	 */
	public TimeElapsedInterceptor() {
		super();
	}

	/**
	 * Reports the elapsed time. The intercepting method.
	 * 
	 * @param invocationContext the invocation context
	 * @return the result
	 */
	@AroundInvoke
	public Object reportTimeElapsed(InvocationContext invocationContext) {

		Object result = null;
		final long start = System.nanoTime();
		try {
			result = invocationContext.proceed();
		} catch (Exception e) {
			// ignore
		}
		final long diff = System.nanoTime() - start;
		final String msg = String.format("Method[%s], time elapsed [%d]ns (reference), [%s]ns (after invoke)",
				invocationContext.getMethod().getName(), getReference(), Tools.formatNumber(diff));
		report.add(msg);
		if (logger.isLoggable(Level.INFO)) {
			logger.info("reportTimeElapsed():");
		}
		return result;
	}

	/**
	 * Gets the reference measure.
	 * 
	 * @return the message
	 */
	private long getReference() {

		long start = System.nanoTime();
		long diff;
		do {
			diff = System.nanoTime() - start;
		} while (diff < PAUSE);
		return diff;
	}
}