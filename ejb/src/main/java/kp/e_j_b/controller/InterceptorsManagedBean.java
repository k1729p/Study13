package kp.e_j_b.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import kp.e_j_b.interceptors.TimeElapsedBean;

/**
 * The CDI managed bean for the {@link TimeElapsedBean} with interceptor
 * {@link kp.e_j_b.interceptors.TimeElapsedInterceptor}.
 *
 */
@Named
@RequestScoped
public class InterceptorsManagedBean {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@EJB
	private static TimeElapsedBean timeElapsedBean;

	/**
	 * The constructor.
	 */
	public InterceptorsManagedBean() {
		super();
	}

	/**
	 * Researches the interceptor.
	 * 
	 * @return the result
	 */
	public String researchInterceptor() {

		timeElapsedBean.pausedMilli();
		timeElapsedBean.pausedNano();
		timeElapsedBean.notPaused();
		if (logger.isLoggable(Level.INFO)) {
			logger.info("researchInterceptor():");
		}
		return "";
	}
}