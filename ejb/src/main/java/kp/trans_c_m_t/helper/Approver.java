package kp.trans_c_m_t.helper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.ejb.EJBContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import kp.trans_c_m_t.service.AuditorBean;

/**
 * The parcel approver.
 *
 */
@Stateless
public class Approver {
	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	@Inject
	private AuditorBean auditorBean;

	@Resource
	private EJBContext ejbContext;

	/**
	 * The constructor.
	 */
	public Approver() {
		super();
	}

	/**
	 * Approves only parcels with the id equal to <b>even</b> value.<br>
	 * Rollbacks all parcels with the id equal to <b>odd</b> value.
	 * 
	 * @param parcelId the parcel id
	 */
	@Transactional
	public void approve(int parcelId) {

		final String msg;
		if (parcelId % 2 == 1) {
			ejbContext.setRollbackOnly();
			msg = String.format("The current transaction is marked for rollback, parcelId[%d].", parcelId);
			report.add(msg);
		} else {
			auditorBean.recordApproved();
			msg = "OK";
		}
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("approve(): %s", msg));
		}
	}
}