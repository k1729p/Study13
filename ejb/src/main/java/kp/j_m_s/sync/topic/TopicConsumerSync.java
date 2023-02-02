package kp.j_m_s.sync.topic;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.Topic;

import kp.j_m_s.sync.ConsumerSync;

/**
 * The JMS messages synchronous <b>topic consumer</b>.
 *
 */
@Named
@RequestScoped
public class TopicConsumerSync extends ConsumerSync {
	@Inject
	private Logger logger;

	@Resource(lookup = "jms/StudyTopicSync")
	private Topic topic;

	/**
	 * The constructor.
	 */
	public TopicConsumerSync() {
		super();
	}

	/**
	 * Receives the topic messages.
	 * 
	 * @return the result
	 */
	public String receiveTopicMessages() {
		receiveMessages(topic, "topic");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("receiveTopicMessages():");
		}
		return "";
	}
}