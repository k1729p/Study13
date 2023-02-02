package kp.j_p_a.controller;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;

import kp.j_p_a.domain.units.Side;
import kp.j_p_a.domain.units.Unit;

/**
 * The CDI managed bean for the {@link Unit}.
 *
 */
@Named
@RequestScoped
public class UnitManagedBean {
	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	@Inject
	private EntityManager entityManager;

	private CriteriaBuilder criteriaBuilder;

	/**
	 * The constructor.
	 */
	public UnitManagedBean() {
		super();
	}

	/**
	 * Initializes bean.
	 * 
	 */
	@PostConstruct
	private void init() {
		criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	/**
	 * Creates, reads, and deletes the {@link Unit}s.
	 * 
	 * @return the result
	 */
	@Transactional
	public String createReadDeleteUnits() {

		report.clear();
		final long count = reportCounts();
		if (count == 0) {
			createUnits();
		} else {
			readAndDeleteUnits();
		}
		reportCounts();
		return "";
	}

	/**
	 * Reports the counts of the {@link Unit}s.
	 * 
	 * @return the count
	 */
	private long reportCounts() {

		final CriteriaQuery<Tuple> tupleCriteria = criteriaBuilder.createTupleQuery();
		tupleCriteria.select(criteriaBuilder.tuple(criteriaBuilder.countDistinct(tupleCriteria.from(Unit.class)),
				criteriaBuilder.countDistinct(tupleCriteria.from(Side.class))));
		final TypedQuery<Tuple> tupleQuery = entityManager.createQuery(tupleCriteria);
		final Tuple tuple = tupleQuery.getSingleResult();

		report.add(String.format("Total units count[%s], total sides count[%s]", tuple.get(0), tuple.get(1)));
		return (long) tuple.get(0);
	}

	/**
	 * Creates the {@link Unit}s.
	 * 
	 */
	private void createUnits() {

		final Unit unitA = new Unit("A");
		final Unit unitB = new Unit("B", unitA);
		final Unit unitC = new Unit("C", unitB);

		final Unit unitX = new Unit("X");
		unitC.addChild(unitX);
		final Unit unitY = new Unit("Y");
		unitC.addChild(unitY);
		final Unit unitZ = new Unit("Z");
		unitC.addChild(unitZ);

		report.add("Before Persist");
		entityManager.persist(unitA);
		entityManager.persist(unitB);
		entityManager.persist(unitC);
		report.add("After Persist");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("createUnits():");
		}
	}

	/**
	 * Reads and deletes the {@link Unit}s.
	 * 
	 */
	private void readAndDeleteUnits() {

		final Unit unitA = entityManager.find(Unit.class, "A");
		final Unit unitB = unitA.getNext();
		final Unit unitC = unitB.getNext();
		final Iterator<Unit> iterator = unitC.getChildren().iterator();
		final Unit unitX = iterator.next();
		final Unit unitY = iterator.next();
		final Unit unitZ = iterator.next();

		report.add(unitA.toString());
		report.add(unitB.toString());
		report.add(unitC.toString());
		report.add(unitX.toString());
		report.add(unitY.toString());
		report.add(unitZ.toString());

		report.add("Before Remove");
		entityManager.remove(unitA);
		report.add("After Remove");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("readAndDeleteUnits():");
		}
	}
}