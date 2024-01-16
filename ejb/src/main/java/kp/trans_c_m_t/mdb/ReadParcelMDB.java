package kp.trans_c_m_t.mdb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import kp.trans_c_m_t.service.ParcelAdministratorBean;

/**
 * The message-driven bean for the <B>parcel</B> list reading.
 * 
 */
@MessageDriven(activationConfig = { //
		@ActivationConfigProperty( //
				propertyName = "destinationLookup", propertyValue = "jms/ReadParcelQueue"), //
		@ActivationConfigProperty( //
				propertyName = "destinationType", propertyValue = "jakarta.jms.Queue") })
public class ReadParcelMDB implements MessageListener {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	ParcelAdministratorBean parcelAdministratorBean;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private List<String> report;

	/**
	 * The constructor.
	 */
	public ReadParcelMDB() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void onMessage(Message message) {

		report.add("ReadParcelMDB received message from 'ReadParcelQueue'.");
		parcelAdministratorBean.read();
		if (logger.isLoggable(Level.INFO)) {
			logger.info("onMessage():");
		}
	}
}