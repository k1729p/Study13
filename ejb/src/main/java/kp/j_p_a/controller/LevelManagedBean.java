package kp.j_p_a.controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import kp.j_p_a.domain.levels.FirstLevel;
import kp.j_p_a.domain.levels.FirstLevel_;
import kp.j_p_a.domain.levels.FourthLevel;
import kp.j_p_a.domain.levels.Level_;
import kp.j_p_a.domain.levels.SecondLevel;
import kp.j_p_a.domain.levels.SecondLevel_;
import kp.j_p_a.domain.levels.ThirdLevel;
import kp.j_p_a.domain.levels.ThirdLevel_;

/**
 * The CDI managed bean for the {@link Level}.
 * <p>
 * Uses metamodel queries.
 *
 */
@Named
@RequestScoped
public class LevelManagedBean {
	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	@Inject
	private EntityManager entityManager;

	private CriteriaBuilder criteriaBuilder;

	private static final int NUMBER_OF_LEVELS = 4;

	/**
	 * The constructor.
	 */
	public LevelManagedBean() {
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
	 * Creates, reads, and deletes the {@link Level}s.
	 * 
	 * @return the result
	 */
	@Transactional
	public String createReadDeleteLevels() {

		report.clear();
		long count = reportCounts();
		if (count == 0) {
			createLevels();
		} else {
			readWithQueryUsingJoins();
			readWithCriteriaUsingJoins();
			readWithCriteriaUsingAggregateFunctions();
			deleteLevels();
		}
		reportCounts();
		if (logger.isLoggable(Level.INFO)) {
			logger.info("createReadDeleteLevels():");
		}
		return "";
	}

	/**
	 * Reports counts.
	 * 
	 * @return count
	 */
	private long reportCounts() {

		final CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
		countCriteria.select(criteriaBuilder.countDistinct(countCriteria.from(FirstLevel.class)));
		final TypedQuery<Long> countQuery = entityManager.createQuery(countCriteria);
		return countQuery.getSingleResult();
	}

	/**
	 * Creates levels.
	 * 
	 */
	private void createLevels() {

		report.add("Before Persist");
		for (int i = 1; i <= NUMBER_OF_LEVELS; i++) {
			final FirstLevel firstLevel = new FirstLevel();
			entityManager.persist(firstLevel);
			firstLevel.setText(String.format("item %d", i));
			for (int j = 1; j <= NUMBER_OF_LEVELS; j++) {
				final SecondLevel secondLevel = new SecondLevel();
				entityManager.persist(secondLevel);
				secondLevel.setText(String.format("item %d.%d", i, i * j));
				firstLevel.getSecondLevels().add(secondLevel);
				for (int k = 1; k <= NUMBER_OF_LEVELS; k++) {
					final ThirdLevel thirdLevel = new ThirdLevel();
					entityManager.persist(thirdLevel);
					thirdLevel.setText(String.format("item %d.%d.%d", i, i * j, i * j * k));
					secondLevel.getThirdLevels().add(thirdLevel);
					for (int m = 1; m <= NUMBER_OF_LEVELS; m++) {
						final FourthLevel fourthLevel = new FourthLevel();
						entityManager.persist(fourthLevel);
						fourthLevel.setText(String.format("item %d.%d.%d.%d", i, i * j, i * j * k, i * j * k * m));
						thirdLevel.getFourthLevels().add(fourthLevel);
					}
				}
			}
		}
		report.add("After Persist");
	}

	/**
	 * Reads levels with query using joins.
	 * 
	 */
	private void readWithQueryUsingJoins() {

		final Map<String, String> queryMap = new LinkedHashMap<>();
		queryMap.put("*** String Query with 'JOIN' ***", """
				    SELECT DISTINCT fst FROM FirstLevel fst JOIN fst.secondLevels snd
				    JOIN snd.thirdLevels trd JOIN trd.fourthLevels fth
				    WHERE fth.text IN('item 1.1.1.1', 'item 4.16.64.256')
				    ORDER BY fst.id
				""");
		queryMap.put("*** String Query with 'IN' ***", """
				    SELECT DISTINCT fst FROM FirstLevel fst, IN(fst.secondLevels) snd,
				    IN(snd.thirdLevels) trd, IN(trd.fourthLevels) fth
				    WHERE fth.text IN('item 1.1.1.1', 'item 4.16.64.256')
				    ORDER BY fst.id
				""");
		queryMap.entrySet().forEach(entry -> {
			final TypedQuery<FirstLevel> query = entityManager.createQuery(entry.getValue(), FirstLevel.class);

			final List<FirstLevel> firstLevelList = query.getResultList();
			readList(firstLevelList, entry.getKey());
		});
	}

	/**
	 * Reads levels with {@link CriteriaQuery} using joins.
	 * 
	 */
	private void readWithCriteriaUsingJoins() {

		final CriteriaQuery<FirstLevel> criteria = criteriaBuilder.createQuery(FirstLevel.class);
		final Root<FirstLevel> firstRoot = criteria.from(FirstLevel.class);
		final Join<FirstLevel, SecondLevel> secondJoin = firstRoot.join(FirstLevel_.secondLevels);
		final Join<SecondLevel, ThirdLevel> thirdJoin = secondJoin.join(SecondLevel_.thirdLevels);
		final Join<ThirdLevel, FourthLevel> fourthJoin = thirdJoin.join(ThirdLevel_.fourthLevels);
		criteria.select(firstRoot).distinct(true);
		criteria.where(fourthJoin.get(Level_.text).in("item 1.1.1.1", "item 4.16.64.256"));
		criteria.orderBy(criteriaBuilder.asc(firstRoot.get(Level_.id)));
		final TypedQuery<FirstLevel> query = entityManager.createQuery(criteria);

		final List<FirstLevel> firstLevelList = query.getResultList();
		readList(firstLevelList, "*** Criteria Query with 'join' ***");
	}

	/**
	 * Reads the {@link FirstLevel} list.
	 * 
	 * @param firstLevelList the {@link FirstLevel} list
	 * @param label          the label
	 */
	private void readList(List<FirstLevel> firstLevelList, String label) {

		report.add(label);
		/*
		 * First Element
		 */
		final FirstLevel firstLevelF = firstLevelList.get(0);
		if (Objects.isNull(firstLevelF)) {
			return;
		}
		final Iterator<SecondLevel> secondIterF = firstLevelF.getSecondLevels().iterator();
		if (!secondIterF.hasNext()) {
			return;
		}
		final SecondLevel secondLevelF = secondIterF.next();
		final Iterator<ThirdLevel> thirdIterF = secondLevelF.getThirdLevels().iterator();
		if (!thirdIterF.hasNext()) {
			return;
		}
		final ThirdLevel thirdLevelF = thirdIterF.next();
		final Iterator<FourthLevel> fourthIterF = thirdLevelF.getFourthLevels().iterator();
		if (!fourthIterF.hasNext()) {
			return;
		}
		final FourthLevel fourthLevelF = fourthIterF.next();
		report.add(String.format(
				"1st Level: id[%d], text[%s], 2nd Level: id[%d], text[%s], "
						+ "3rd Level: id[%d], text[%s], 4th Level: id[%d], text[%s]",
				firstLevelF.getId(), firstLevelF.getText(), secondLevelF.getId(), secondLevelF.getText(),
				thirdLevelF.getId(), thirdLevelF.getText(), fourthLevelF.getId(), fourthLevelF.getText()));
		/*
		 * Last Element
		 */
		if (firstLevelList.size() == 1) {
			return;
		}
		final FirstLevel firstLevelL = firstLevelList.get(firstLevelList.size() - 1);
		if (Objects.isNull(firstLevelL)) {
			return;
		}
		final Iterator<SecondLevel> secondIterL = firstLevelL.getSecondLevels().iterator();
		SecondLevel secondLevelL = null;
		while (secondIterL.hasNext()) {
			secondLevelL = secondIterL.next();
		}
		if (Objects.isNull(secondLevelL)) {
			return;
		}
		final Iterator<ThirdLevel> thirdIterL = secondLevelL.getThirdLevels().iterator();
		ThirdLevel thirdLevelL = null;
		while (thirdIterL.hasNext()) {
			thirdLevelL = thirdIterL.next();
		}
		if (Objects.isNull(thirdLevelL)) {
			return;
		}
		final Iterator<FourthLevel> fourthIterL = thirdLevelL.getFourthLevels().iterator();
		FourthLevel fourthLevelL = null;
		while (fourthIterL.hasNext()) {
			fourthLevelL = fourthIterL.next();
		}
		if (Objects.isNull(fourthLevelL)) {
			return;
		}
		report.add(String.format(
				"1st Level: id[%d], text[%s], 2nd Level: id[%d], text[%s], "
						+ "3rd Level: id[%d], text[%s], 4th Level: id[%d], text[%s]",
				firstLevelL.getId(), firstLevelL.getText(), secondLevelL.getId(), secondLevelL.getText(),
				thirdLevelL.getId(), thirdLevelL.getText(), fourthLevelL.getId(), fourthLevelL.getText()));
	}

	/**
	 * Reads the levels with the {@link CriteriaQuery} using aggregate functions.
	 * 
	 */
	private void readWithCriteriaUsingAggregateFunctions() {

		final CriteriaQuery<Object[]> criteria = prepareCriteriaWithAggregateFunctions();
		final TypedQuery<Object[]> query = entityManager.createQuery(criteria);
		final List<Object[]> resultList = query.getResultList();

		report.add("*** Criteria Query with 'groupBy' ***");
		for (Object[] result : resultList) {
			report.add(String.format(
					"1st Level id[%s], 2nd Level modulo 3[%s], 3rd Level modulo 3[%s], 4th Level count[%s]", //
					result[0], result[1], result[2], result[3]));
		}
	}

	/**
	 * Prepares the {@link CriteriaQuery} with aggregate functions.
	 * 
	 * @return the {@link CriteriaQuery}
	 */
	private CriteriaQuery<Object[]> prepareCriteriaWithAggregateFunctions() {

		final CriteriaQuery<Object[]> criteria = criteriaBuilder.createQuery(Object[].class);
		final Root<FirstLevel> firstRoot = criteria.from(FirstLevel.class);
		final Join<FirstLevel, SecondLevel> secondJoin = firstRoot.join(FirstLevel_.secondLevels);
		final Join<SecondLevel, ThirdLevel> thirdJoin = secondJoin.join(SecondLevel_.thirdLevels);
		final Join<ThirdLevel, FourthLevel> fourthJoin = thirdJoin.join(ThirdLevel_.fourthLevels);

		final Path<Integer> pathIdFirst = firstRoot.get(Level_.id);
		// modulo operation
		final Expression<Integer> modulusSecond = criteriaBuilder.mod(secondJoin.get(Level_.id), 3);
		// modulo operation
		final Expression<Integer> modulusThird = criteriaBuilder.mod(thirdJoin.get(Level_.id), 3);
		final Expression<Long> countForth = criteriaBuilder.countDistinct(fourthJoin.get(Level_.id));

		criteria.multiselect(pathIdFirst, modulusSecond, modulusThird, countForth);
		criteria.groupBy(pathIdFirst, modulusSecond, modulusThird);
		criteria.having(firstRoot.get(Level_.text).in("item 1", "item 2", "item 3"));
		criteria.where(firstRoot.get(Level_.text).in("item 2", "item 3", "item 4"));
		criteria.orderBy(criteriaBuilder.asc(pathIdFirst), criteriaBuilder.asc(modulusSecond),
				criteriaBuilder.asc(modulusThird));
		return criteria;
	}

	/**
	 * Deletes level.
	 * 
	 */
	private void deleteLevels() {

		report.add("Before Remove");
		final CriteriaDelete<FirstLevel> deleteCriteria = criteriaBuilder.createCriteriaDelete(FirstLevel.class);
		deleteCriteria.from(FirstLevel.class);
		entityManager.createQuery(deleteCriteria).executeUpdate();
		report.add("After Remove");

	}
}