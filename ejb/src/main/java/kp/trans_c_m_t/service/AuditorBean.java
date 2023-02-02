package kp.trans_c_m_t.service;

import static jakarta.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;

import kp.trans_c_m_t.domain.Audit;

/**
 * The <b>stateless</b> session bean for the <b>auditor</b>.
 * 
 */
@Stateless
public class AuditorBean {
	/*-
	 With the 'RequiresNew' attribute the container
	 suspends the current transaction and starts a new transaction.
	 The Java EE transaction manager does not support nested transactions.
	 */
	@Inject
	private List<String> report;

	@Inject
	private EntityManager entityManager;

	/**
	 * The constructor.
	 */
	public AuditorBean() {
		super();
	}

	/**
	 * Records the state 'created'.<br>
	 * The parcel status: created but not approved yet.
	 * 
	 */
	@TransactionAttribute(REQUIRES_NEW)
	public void recordCreated() {

		final Audit audit = loadAudit();
		audit.setCreated(audit.getCreated() + 1);
	}

	/**
	 * Records the state 'approved'.<br>
	 * The parcel status: created and approved.
	 * 
	 */
	@TransactionAttribute(REQUIRES_NEW)
	public void recordApproved() {

		final Audit audit = loadAudit();
		audit.setApproved(audit.getApproved() + 1);
	}

	/**
	 * Shows the {@link Audit}.
	 * 
	 */
	public void showAudit() {

		final Audit audit = loadAudit();
		report.add(String.format("Audit: parcels created[%d], parcels approved[%d].", audit.getCreated(),
				audit.getApproved()));
	}

	/**
	 * Loads the {@link Audit}.
	 * 
	 * @return the {@link Audit}
	 */
	@Transactional
	private Audit loadAudit() {

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Audit> criteriaQuery = criteriaBuilder.createQuery(Audit.class);
		criteriaQuery.from(Audit.class);
		final TypedQuery<Audit> typedQuery = entityManager.createQuery(criteriaQuery);

		final List<Audit> auditList = typedQuery.getResultList();
		if (auditList.isEmpty()) {
			final Audit audit = new Audit();
			entityManager.persist(audit);
			return audit;
		}
		return auditList.get(0);

	}
}