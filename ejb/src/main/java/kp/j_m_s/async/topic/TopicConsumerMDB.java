package kp.j_m_s.async.topic;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import kp.j_m_s.async.ConsumerAsync;

/**
 * The message-driven bean servicing <b>topic</b>.
 * 
 */
@MessageDriven(activationConfig = { /*-*/
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/StudyTopicAsync"), /*-*/
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic") })
public class TopicConsumerMDB extends ConsumerAsync implements MessageListener {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	/**
	 * The constructor.
	 */
	public TopicConsumerMDB() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void onMessage(Message message) {
		process(message, "topic");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("onMessage():");
		}
	}
}