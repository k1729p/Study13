package kp.trans_c_m_t.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import kp.trans_c_m_t.domain.Parcel;

/**
 * The <b>stateless</b> session bean for the {@link Parcel} administrator.
 *
 */
@Stateless
public class ParcelAdministratorBean {
	/*-
	 Using Container-Managed Transactions.
	 
	 Transaction Attributes: the 'Required' attribute is the implicit transaction attribute
	 for all enterprise bean methods running with container-managed transaction demarcation. 
	*/
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private List<String> report;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private AuditorBean auditorBean;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private EntityManager entityManager;

	/**
	 * The constructor.
	 */
	public ParcelAdministratorBean() {
		super();
	}

	/**
	 * Creates the {@link Parcel}.
	 * 
	 * @param text the text
	 * @return the {@link Parcel} id
	 */
	@Transactional
	public int create(String text) {

		final Parcel parcel = new Parcel(text);
		entityManager.persist(parcel);
		report.add(String.format("Created parcel: id[%d], text[%s].", parcel.getId(), parcel.getText()));
		auditorBean.recordCreated();
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("create(): parcel: id[%d], text[%s].", parcel.getId(), parcel.getText()));
		}
		return parcel.getId();
	}

	/**
	 * Reads the {@link Parcel} list.
	 * 
	 */
	public void read() {

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Parcel> criteriaQuery = criteriaBuilder.createQuery(Parcel.class);
		criteriaQuery.from(Parcel.class);
		final TypedQuery<Parcel> typedQuery = entityManager.createQuery(criteriaQuery);
		final List<Parcel> parcelList = typedQuery.getResultList();
		for (Parcel parcel : parcelList) {
			report.add(String.format("parcel: id[%d], text[%s].", parcel.getId(), parcel.getText()));
		}
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("read(): parcelList size[%d]", parcelList.size()));
		}
	}
}