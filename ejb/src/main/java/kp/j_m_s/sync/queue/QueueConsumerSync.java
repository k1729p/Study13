package kp.j_m_s.sync.queue;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.Queue;
import kp.j_m_s.sync.ConsumerSync;

/**
 * The JMS messages synchronous <b>queue consumer</b>.
 *
 */
@Named
@RequestScoped
public class QueueConsumerSync extends ConsumerSync {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@Resource(lookup = "jms/StudyQueueSync")
	private Queue queue;

	/**
	 * The constructor.
	 */
	public QueueConsumerSync() {
		super();
	}

	/**
	 * Receives queue messages.
	 * 
	 * @return the result
	 */
	public String receiveQueueMessages() {
		receiveMessages(queue, "queue");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("receiveQueueMessages():");
		}
		return "";
	}
}