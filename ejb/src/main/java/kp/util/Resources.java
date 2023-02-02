package kp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * The resources.
 *
 */
public class Resources {
	/**
	 * The constructor.
	 */
	public Resources() {
		super();
	}

	/**
	 * Produces the logger.
	 * 
	 * @param injectionPoint the injectionPoint
	 * @return the logger
	 */
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint) {
		final String category = injectionPoint.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}

	/**
	 * Produces the report.
	 */
	@Named
	@Produces
	protected static final List<String> report = new ArrayList<>();

	/**
	 * Produces the entity manager.
	 */
	@PersistenceContext
	@Produces
	private EntityManager entityManager;
}