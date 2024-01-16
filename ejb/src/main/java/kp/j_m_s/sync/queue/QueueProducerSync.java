package kp.j_m_s.sync.queue;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.Queue;
import kp.j_m_s.Producer;

/**
 * The JMS messages <b>queue producer</b> (for synchronous consumer).
 *
 */
@Named
@RequestScoped
public class QueueProducerSync extends Producer {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@Resource(lookup = "jms/StudyQueueSync")
	private Queue queue;

	/**
	 * The constructor.
	 */
	public QueueProducerSync() {
		super();
	}

	/**
	 * Sends queue messages.
	 * 
	 * @return the result
	 */
	public String sendQueueMessages() {
		sendMessages(queue, "queue");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("sendQueueMessages():");
		}
		return "";
	}
}