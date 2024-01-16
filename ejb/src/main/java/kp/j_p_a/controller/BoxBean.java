package kp.j_p_a.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.EJBContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import kp.Constants;
import kp.j_p_a.domain.boxes.CentralBox;
import kp.j_p_a.domain.boxes.LowerBox;
import kp.j_p_a.domain.boxes.MultiBox;
import kp.j_p_a.domain.boxes.SingleBox;
import kp.j_p_a.domain.boxes.UpperBox;
import kp.j_p_a.domain.components.TermDates;
import kp.util.Tools;

/**
 * The stateless session bean for <B>box</B>.<BR>
 * Uses container-managed transactions.
 *
 */
@Named
@Stateless
public class BoxBean {

	private static final boolean USE_NATIVE_QUERY = true;
	private static final boolean USE_CRITERIA = true;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private List<String> report;

	@Resource
	private EJBContext ejbContext;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private EntityManager entityManager;

	private CriteriaBuilder criteriaBuilder;

	private static final Class<?>[] BOX_CLASSES_ARR = { CentralBox.class, UpperBox.class, SingleBox.class,
			MultiBox.class, LowerBox.class };
	private static final List<String> MUTABLE_TEXT_LIST = new ArrayList<>(Tools.getTextList());
	private static final String IDENTIFIER = "identifier";

	/**
	 * The constructor.
	 */
	public BoxBean() {
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
	 * Creates the {@link CentralBox}.
	 * 
	 * @return the result
	 */
	public String createCentralBox() {

		/*
		 * Method uses bean-managed transaction.
		 */
		report.clear();
		try {
			final CentralBox centralBox = createAndFillCentralBox();
			if (getNumberOfCentralBoxes() > 3) {
				// simulate validation error
				ejbContext.setRollbackOnly();
				final String msg = "The maximum number of boxes is 3. The creating was aborted and the transaction was marked for rollback.";
				report.add(msg);
				if (logger.isLoggable(Level.WARNING)) {
					logger.warning(String.format("createCentralBox(): %s", msg));
				}
				return "";
			}
			loadLinesFromBox(centralBox);
		} catch (Exception e) {
			logger.severe(String.format("createCentralBox(): exception[%s]", e.getMessage()));
			return "";
		}
		if (logger.isLoggable(Level.INFO)) {
			logger.info("createCentralBox():");
		}
		return "";
	}

	/**
	 * Reads the {@link CentralBox} list.
	 * 
	 * @return the result
	 */
	public String readCentralBoxes() {

		report.clear();
		showBoxesCount();
		final List<CentralBox> centralBoxList = findCentralBoxes();
		if (centralBoxList.isEmpty()) {
			report.add(Constants.EMPTY);
			if (logger.isLoggable(Level.INFO)) {
				logger.info("readCentralBoxes(): empty");
			}
			return "";
		}
		for (CentralBox centralBox : centralBoxList) {
			report.add(Constants.LINE);
			loadLinesFromBox(centralBox);
		}
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("readCentralBoxes(): centralBoxList size[%d]", centralBoxList.size()));
		}
		return "";
	}

	/**
	 * Updates the {@link CentralBox} list.
	 * 
	 * @return the result
	 */
	public String updateCentralBoxes() {

		/*
		 * Method uses bean-managed transaction.
		 */
		report.clear();
		showBoxesCount();
		if (getNumberOfCentralBoxes() < 1) {
			report.add(Constants.EMPTY);
			if (logger.isLoggable(Level.INFO)) {
				logger.info("updateCentralBoxes(): nothing to update");
			}
			return "";
		}
		List<CentralBox> centralBoxList;
		try {
			centralBoxList = findCentralBoxes();
			for (CentralBox centralBox : centralBoxList) {
				centralBox.setCardinalDirection(centralBox.getCardinalDirection().getNext());
				entityManager.merge(centralBox);
			}
		} catch (Exception e) {
			logger.severe(String.format("updateCentralBoxes(): exception[%s]", e.getMessage()));
			return "";
		}
		for (CentralBox centralBox : centralBoxList) {
			report.add(Constants.LINE);
			loadLinesFromBox(centralBox);
		}
		if (logger.isLoggable(Level.INFO)) {
			logger.info("updateCentralBoxes():");
		}
		return "";
	}

	/**
	 * Deletes the {@link CentralBox}.
	 * 
	 * @return the result
	 */
	public String deleteCentralBox() {

		report.clear();
		if (getNumberOfCentralBoxes() < 1) {
			showBoxesCount();
			report.add(Constants.EMPTY);
			if (logger.isLoggable(Level.INFO)) {
				logger.info("deleteCentralBox(): nothing to delete");
			}
			return "";
		}
		try {
			final CentralBox centralBox = findCentralBoxForRemove();
			entityManager.remove(centralBox);
		} catch (Exception e) {
			logger.severe(String.format("deleteCentralBox(): exception[%s]", e.getMessage()));
			return "";
		}
		showBoxesCount();
		if (logger.isLoggable(Level.INFO)) {
			logger.info("deleteCentralBox():");
		}
		return "";
	}

	/**
	 * Creates and fills the {@link CentralBox}.
	 * 
	 * @return the {@link CentralBox}.
	 */
	private CentralBox createAndFillCentralBox() {

		final CentralBox centralBox = new CentralBox();
		entityManager.persist(centralBox);
		centralBox.setText(MUTABLE_TEXT_LIST.getFirst());
		centralBox.setTermDates(new TermDates());

		addUpperBox(centralBox);

		final String label = String.format("created by %s", MUTABLE_TEXT_LIST.getFirst());

		final SingleBox singleBox = new SingleBox();
		singleBox.setText(label);
		singleBox.setCentralBox(centralBox);
		centralBox.setSingleBox(singleBox);

		createMultiplicity(centralBox, label);

		for (int i = 1; i <= 2; i++) {
			final LowerBox lowerBox = new LowerBox();
			lowerBox.setText(String.format("%s - item %d", label, i));
			centralBox.addLowerBox(lowerBox);
		}
		entityManager.merge(centralBox);
		Collections.rotate(MUTABLE_TEXT_LIST, -1);
		return centralBox;
	}

	/**
	 * Adds the {@link UpperBox}.
	 * 
	 * @param centralBox the {@link CentralBox}.
	 */
	private void addUpperBox(CentralBox centralBox) {

		final CriteriaQuery<UpperBox> upperBoxCriteria = criteriaBuilder.createQuery(UpperBox.class);
		upperBoxCriteria.select(upperBoxCriteria.from(UpperBox.class));
		final TypedQuery<UpperBox> upperBoxQuery = entityManager.createQuery(upperBoxCriteria);
		final List<UpperBox> upperBoxList = upperBoxQuery.getResultList();
		final UpperBox upperBox;
		if (upperBoxList.isEmpty()) {
			upperBox = new UpperBox();
			upperBox.setText("root");
		} else {
			upperBox = upperBoxList.getFirst();
		}
		upperBox.addCentralBox(centralBox);
	}

	/**
	 * Creates the multiplicity.
	 * 
	 * @param centralBox the {@link CentralBox}
	 * @param label      the label
	 */
	private void createMultiplicity(CentralBox centralBox, String label) {

		final MultiBox multiBox = new MultiBox();
		multiBox.setText(label);

		final CriteriaQuery<CentralBox> centralBoxCriteria = criteriaBuilder.createQuery(CentralBox.class);
		centralBoxCriteria.select(centralBoxCriteria.from(CentralBox.class));
		final TypedQuery<CentralBox> centralBoxQuery = entityManager.createQuery(centralBoxCriteria);
		final List<CentralBox> centralBoxList = centralBoxQuery.getResultList();
		centralBoxList.forEach(cb -> cb.addMultiBox(multiBox));

		final CriteriaQuery<MultiBox> multiBoxCriteria = criteriaBuilder.createQuery(MultiBox.class);
		multiBoxCriteria.select(multiBoxCriteria.from(MultiBox.class));
		final TypedQuery<MultiBox> multiBoxQuery = entityManager.createQuery(multiBoxCriteria);
		final List<MultiBox> multiBoxList = multiBoxQuery.getResultList();
		multiBoxList.forEach(centralBox::addMultiBox);
	}

	/**
	 * Finds the {@link CentralBox} list.
	 * 
	 * @return the centralBoxList
	 */
	private List<CentralBox> findCentralBoxes() {

		if (USE_NATIVE_QUERY) {
			return prepareAndExecuteNativeQuery();
		}
		TypedQuery<CentralBox> query;
		if (USE_CRITERIA) {
			final CriteriaQuery<CentralBox> centralBoxCriteria = criteriaBuilder.createQuery(CentralBox.class);
			final Root<CentralBox> centralBoxRoot = centralBoxCriteria.from(CentralBox.class);
			centralBoxRoot.fetch("multiBoxes");
			centralBoxRoot.fetch("lowerBoxes");
			final Order order = criteriaBuilder.asc(centralBoxRoot.get(IDENTIFIER));
			centralBoxCriteria.select(centralBoxRoot).distinct(true).orderBy(order);
			query = entityManager.createQuery(centralBoxCriteria);
		} else {
			query = entityManager.createNamedQuery("CentralBox.findAll", CentralBox.class);
		}
		return query.getResultList();
	}

	/**
	 * Finds the {@link CentralBox} list using native query.<br>
	 * It is the temporary fix, because 'CriteriaQuery' and 'NamedQuery' both fail
	 * on current server 'Wildfly-27.0.0.Beta1'.
	 * 
	 * @return the centralBoxList
	 */
	@SuppressWarnings("unchecked")
	private List<CentralBox> prepareAndExecuteNativeQuery() {
		Query query = entityManager.createNativeQuery("""
				SELECT DISTINCT CB.* FROM CENTRALBOX CB ORDER BY CB.IDENTIFIER ASC
				""", CentralBox.class);
		return query.getResultList();
	}

	/**
	 * Finds the {@link CentralBox} for remove.
	 * 
	 * @return the {@link CentralBox}
	 */
	private CentralBox findCentralBoxForRemove() {

		final TypedQuery<CentralBox> centralBoxQuery;
		if (USE_CRITERIA) {
			final CriteriaQuery<CentralBox> criteriaQuery = criteriaBuilder.createQuery(CentralBox.class);
			final Root<CentralBox> centralBoxRoot = criteriaQuery.from(CentralBox.class);
			final Subquery<CentralBox> subQuery = criteriaQuery.subquery(CentralBox.class);
			final Root<CentralBox> centralBoxSubRoot = subQuery.from(CentralBox.class);
			subQuery.select(centralBoxSubRoot.get(IDENTIFIER)).distinct(true);
			final Predicate predicate = criteriaBuilder.lessThanOrEqualTo(centralBoxRoot.get(IDENTIFIER),
					criteriaBuilder.all(subQuery));
			criteriaQuery.where(predicate);
			centralBoxQuery = entityManager.createQuery(criteriaQuery);
		} else {
			centralBoxQuery = entityManager.createNamedQuery("CentralBox.findFirst", CentralBox.class);
		}
		final CentralBox centralBox = centralBoxQuery.getSingleResult();
		clearMultiplicityBeforeRemove(centralBox);
		return centralBox;
	}

	/**
	 * Clears the multiplicity before remove.
	 * 
	 * @param centralBox the {@link CentralBox}
	 */
	private void clearMultiplicityBeforeRemove(CentralBox centralBox) {

		new ArrayList<>(centralBox.getMultiBoxes()).forEach(centralBox::removeMultiBox);

		final CriteriaQuery<MultiBox> multiBoxCriteria = criteriaBuilder.createQuery(MultiBox.class);
		multiBoxCriteria.select(multiBoxCriteria.from(MultiBox.class));
		final TypedQuery<MultiBox> multiBoxQuery = entityManager.createQuery(multiBoxCriteria);
		final List<MultiBox> multiBoxList = multiBoxQuery.getResultList();

		final CriteriaQuery<CentralBox> centralBoxCriteria = criteriaBuilder.createQuery(CentralBox.class);
		centralBoxCriteria.select(centralBoxCriteria.from(CentralBox.class));
		final TypedQuery<CentralBox> centralBoxQuery = entityManager.createQuery(centralBoxCriteria);
		final List<CentralBox> centralBoxList = centralBoxQuery.getResultList();

		for (MultiBox multiBox : multiBoxList) {
			if (!multiBox.getCentralBoxes().isEmpty()) {
				continue;
			}
			// remove empty multi box
			centralBoxList.forEach(cb -> cb.removeMultiBox(multiBox));
			entityManager.remove(multiBox);
		}
	}

	/**
	 * Gets the {@link CentralBox} number.
	 * 
	 * @return the number
	 */
	private long getNumberOfCentralBoxes() {

		final CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
		countCriteria.select(criteriaBuilder.countDistinct(countCriteria.from(CentralBox.class)));
		final TypedQuery<Long> countQuery = entityManager.createQuery(countCriteria);
		return countQuery.getSingleResult();
	}

	/**
	 * Loads lines from the box.
	 * 
	 * @param centralBox the {@link CentralBox}
	 */
	private void loadLinesFromBox(CentralBox centralBox) {

		report.add(String.format("centralBox: identifier[%d], text[%s], cardinalDirection[%s]",
				centralBox.getIdentifier(), centralBox.getText(), centralBox.getCardinalDirection()));
		final TermDates termDates = centralBox.getTermDates();
		report.add(String.format("localDate[%s], localDateTime[%s]",
				termDates.getLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
				termDates.getLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

		/*
		 * Many-To-One
		 */
		final UpperBox upperBox = centralBox.getUpperBox();
		if (Objects.nonNull(upperBox)) {
			report.add(String.format("'Many-To-One' upperBox: identifier[%d], text[%s]", upperBox.getIdentifier(),
					upperBox.getText()));
		} else {
			report.add("'Many-To-One' NULL upperBox");
		}
		/*
		 * One-To-One
		 */
		final SingleBox singleBox = centralBox.getSingleBox();
		if (Objects.nonNull(singleBox)) {
			report.add(String.format("'One-To-One' singleBox: identifier[%d], text[%s]", singleBox.getIdentifier(),
					singleBox.getText()));
		} else {
			report.add("'One-To-One' NULL singleBox");
		}
		/*
		 * Many-To-Many
		 */
		final Set<MultiBox> multiBoxes = centralBox.getMultiBoxes();
		if (!multiBoxes.isEmpty()) {
			for (MultiBox multiBox : multiBoxes) {
				report.add(String.format("'Many-To-Many' multiBox: identifier[%d], text[%s]", multiBox.getIdentifier(),
						multiBox.getText()));
			}
		} else {
			report.add("'Many-To-Many' EMPTY multiBoxes");
		}
		/*
		 * One-To-Many
		 */
		final Set<LowerBox> lowerBoxes = centralBox.getLowerBoxes();
		if (!lowerBoxes.isEmpty()) {
			for (LowerBox lowerBox : lowerBoxes) {
				report.add(String.format("'One-To-Many' lowerBox: identifier[%d], text[%s]", lowerBox.getIdentifier(),
						lowerBox.getText()));
			}
		} else {
			report.add("'One-To-Many' EMPTY lowerBoxes");
		}
	}

	/**
	 * Shows the boxes count.
	 * 
	 */
	private void showBoxesCount() {

		final Long[] countArr = new Long[BOX_CLASSES_ARR.length];
		for (int i = 0; i < BOX_CLASSES_ARR.length; i++) {
			final CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
			countCriteria.select(criteriaBuilder.countDistinct(countCriteria.from(BOX_CLASSES_ARR[i])));
			final TypedQuery<Long> countQuery = entityManager.createQuery(countCriteria);
			countArr[i] = countQuery.getSingleResult();
		}
		report.add(String.format(
				"Total count: CentralBoxes[%d], UpperBoxes[%d], SingleBoxes[%d], MultiBoxes[%d], LowerBoxes[%d]",
				countArr[0], countArr[1], countArr[2], countArr[3], countArr[4]));
	}
}