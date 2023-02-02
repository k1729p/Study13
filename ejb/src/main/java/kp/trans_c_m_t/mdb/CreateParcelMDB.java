package kp.trans_c_m_t.mdb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;

import kp.trans_c_m_t.helper.Approver;
import kp.trans_c_m_t.service.ParcelAdministratorBean;

/**
 * The message-driven bean for the <B>parcel</B> creation.
 * 
 */
//SonarQube signals 'duplicated blocks of code'.  But this rule is deprecated in Sonar! 
@MessageDriven(activationConfig = { //
		@ActivationConfigProperty( //
				propertyName = "destinationLookup", propertyValue = "jms/CreateParcelQueue"), //
		@ActivationConfigProperty( //
				propertyName = "destinationType", propertyValue = "jakarta.jms.Queue") })
public class CreateParcelMDB implements MessageListener {
	@Inject
	private Logger logger;

	@Inject
	ParcelAdministratorBean parcelAdministratorBean;

	@Inject
	Approver approver;

	@Inject
	private JMSContext context;

	@Inject
	private List<String> report;

	/**
	 * The constructor.
	 */
	public CreateParcelMDB() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void onMessage(Message message) {

		String text = null;
		try {
			text = ((TextMessage) message).getText();
		} catch (JMSException e) {
			logger.severe(String.format("onMessage(): exception[%s]", e.getMessage()));
			return;
		}
		report.add(String.format("CreateParcelMDB received text[%s] from 'CreateParcelQueue'.", text));
		int parcelId = parcelAdministratorBean.create(text);
		sendConfirmMessage(message, parcelId);
		// last step in current transaction
		approver.approve(parcelId);
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("onMessage(): text[%s], parcelId[%d]", text, parcelId));
		}
	}

	/**
	 * Sends confirm message.
	 * 
	 * @param message  the {@link Message}
	 * @param parcelId the parcel id
	 */
	private void sendConfirmMessage(Message message, int parcelId) {

		String text = null;
		try {
			final Queue confirmQueue = (Queue) message.getJMSReplyTo();
			text = String.format("%s:%d", ((TextMessage) message).getText(), parcelId);
			final Message confirmMessage = context.createTextMessage(text);
			context.createProducer().send(confirmQueue, confirmMessage);
		} catch (JMSException e) {
			logger.severe(String.format("sendConfirmMessage(): exception[%s]", e.getMessage()));
			return;
		}
		report.add(String.format("CreateParcelMDB sent confirm message with text[%s] to 'ConfirmCreateParcelQueue'",
				text));
	}
}