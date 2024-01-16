package kp.j_m_s.async.queue;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import kp.j_m_s.async.ConsumerAsync;

/**
 * The message-driven bean servicing <b>queue</b>.
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/StudyQueueAsync"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue") })
public class QueueConsumerMDB extends ConsumerAsync implements MessageListener {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	/**
	 * The constructor.
	 */
	public QueueConsumerMDB() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void onMessage(Message message) {
		process(message, "queue");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("onMessage():");
		}
	}
}