package kp.trans_b_m_t.service;

import static jakarta.ejb.TransactionManagementType.BEAN;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateful;
import jakarta.ejb.TransactionManagement;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Status;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import kp.Constants;
import kp.trans_b_m_t.domain.Capsule;
import kp.util.Tools;

/**
 * The {@link Capsule} administrator <b>stateful</b> session bean.<BR>
 * The <b>bean-managed</b> transaction management is used.
 * 
 */
@Stateful
@TransactionManagement(BEAN)
public class CapsuleAdministratorBean implements CapsuleAdministrator {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private transient Logger logger;
	/**
	 * The report.
	 */
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private List<String> report;
	/**
	 * The entity manager.
	 */
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private transient EntityManager entityManager;
	/**
	 * The user transaction.
	 */
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private transient UserTransaction userTransaction;

	private static final List<String> MUTABLE_TEXT_LIST = new ArrayList<>(Tools.getTextList());

	/**
	 * The constructor.
	 */
	public CapsuleAdministratorBean() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void create() {
		try {
			if (userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION) {
				userTransaction.begin();
				if (logger.isLoggable(Level.INFO)) {
					logger.info("create(): user transaction begin");
				}
			}
			final String text = MUTABLE_TEXT_LIST.getFirst();
			Collections.rotate(MUTABLE_TEXT_LIST, -1);
			final Capsule capsule = new Capsule(text);
			entityManager.persist(capsule);
			report.add(String.format("Created capsule: id[%d], text[%s].", capsule.getId(), capsule.getText()));
			if (logger.isLoggable(Level.INFO)) {
				logger.info(String.format("create(): capsule: id[%d], text[%s].", capsule.getId(), capsule.getText()));
			}
		} catch (Exception e) {
			logger.severe(String.format("create(): exception[%s]", e.getMessage()));
			rollback();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void read() {

		try {
			if (userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION) {
				userTransaction.begin();
				if (logger.isLoggable(Level.INFO)) {
					logger.info("read(): user transaction begin");
				}
			}
			final List<Capsule> capsuleList = loadCapsuleList();
			if (capsuleList.isEmpty()) {
				report.add(Constants.EMPTY);
			} else {
				for (Capsule capsule : capsuleList) {
					report.add(String.format("capsule: id[%d], text[%s].", capsule.getId(), capsule.getText()));
				}
			}
			if (logger.isLoggable(Level.INFO)) {
				logger.info(String.format("read(): capsuleList size[%d]", capsuleList.size()));
			}
		} catch (Exception e) {
			logger.severe(String.format("read(): exception[%s]", e.getMessage()));
			rollback();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void delete() {

		try {
			if (userTransaction.getStatus() == Status.STATUS_NO_TRANSACTION) {
				userTransaction.begin();
				if (logger.isLoggable(Level.INFO)) {
					logger.info("delete(): user transaction begin");
				}
			}
			final List<Capsule> capsuleList = loadCapsuleList();
			if (capsuleList.isEmpty()) {
				report.add(Constants.EMPTY);
				if (logger.isLoggable(Level.INFO)) {
					logger.info("delete(): empty capsuleList");
				}
				return;
			}
			// delete last in list
			final Capsule capsule = capsuleList.getLast();
			final int id = capsule.getId();
			final String text = capsule.getText();

			entityManager.remove(capsule);
			entityManager.flush();
			report.add(String.format("Deleted capsule: id[%d], text[%s].", id, text));
			if (logger.isLoggable(Level.INFO)) {
				logger.info(String.format("delete(): deleted capsule: id[%d], text[%s].", id, text));
			}
		} catch (Exception e) {
			logger.severe(String.format("delete(): exception[%s]", e.getMessage()));
			rollback();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void commit() {

		try {
			if (userTransaction.getStatus() == Status.STATUS_ACTIVE) {
				userTransaction.commit();
				report.add(String.format("%s COMMIT", Constants.LINE));
			}
		} catch (Exception e) {
			logger.severe(String.format("commit(): exception[%s]", e.getMessage()));
			return;
		}
		if (logger.isLoggable(Level.INFO)) {
			logger.info("commit():");
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void rollback() {

		try {
			if (userTransaction.getStatus() == Status.STATUS_ACTIVE) {
				userTransaction.rollback();
				report.add(String.format("%s ROLLBACK", Constants.LINE));
			}
		} catch (SystemException e) {
			logger.severe(String.format("rollback(): exception[%s]", e.getMessage()));
			return;
		}
		if (logger.isLoggable(Level.INFO)) {
			logger.info("rollback():");
		}
	}

	/**
	 * Loads the {@link Capsule} list.
	 * 
	 * @return the {@link Capsule} list
	 */
	private List<Capsule> loadCapsuleList() {

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Capsule> criteriaQuery = criteriaBuilder.createQuery(Capsule.class);
		final Root<Capsule> capsuleRoot = criteriaQuery.from(Capsule.class);
		final Order order = criteriaBuilder.asc(capsuleRoot.get("id"));
		criteriaQuery.select(capsuleRoot).orderBy(order);
		final TypedQuery<Capsule> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
}