package kp.trans_c_m_t.mdb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 * The message-driven bean for the <B>parcel</B> creation confirmation.
 * 
 */
// SonarQube signals 'duplicated blocks of code'.  But this rule is deprecated in Sonar! 
@MessageDriven(activationConfig = { //
		@ActivationConfigProperty(//
				propertyName = "destinationLookup", propertyValue = "jms/ConfirmCreateParcelQueue"), //
		@ActivationConfigProperty( //
				propertyName = "destinationType", propertyValue = "jakarta.jms.Queue") })
public class ConfirmCreateParcelMDB implements MessageListener {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private List<String> report;

	/**
	 * The constructor.
	 */
	public ConfirmCreateParcelMDB() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void onMessage(Message message) {

		String text;
		try {
			text = ((TextMessage) message).getText();
		} catch (JMSException e) {
			logger.severe(String.format("onMessage(): exception[%s]", e.getMessage()));
			return;
		}
		report.add(String.format("ConfirmCreateParcelMDB received text[%s] from 'ConfirmCreateParcelQueue'.", text));
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("onMessage(): text[%s]", text));
		}
	}
}