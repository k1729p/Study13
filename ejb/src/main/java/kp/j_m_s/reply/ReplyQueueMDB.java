package kp.j_m_s.reply;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

/**
 * The message-driven bean servicing the <b>reply queue</b>.
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/StudyReplyQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue") })
public class ReplyQueueMDB implements MessageListener {
	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	/**
	 * The constructor.
	 */
	public ReplyQueueMDB() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void onMessage(Message message) {

		String label = null;
		try {
			label = message.getStringProperty("label");
		} catch (JMSException e) {
			logger.severe(String.format("onMessage(): JMSException[%s]", e.getMessage()));
			return;
		}
		report.add(String.format("'reply queue' received CONTROL message, label[%s]", label));
		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("onMessage(): label[%s]", label));
		}
	}
}