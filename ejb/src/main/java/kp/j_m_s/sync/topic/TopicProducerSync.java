package kp.j_m_s.sync.topic;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.Topic;

import kp.j_m_s.Producer;

/**
 * The JMS messages <b>topic producer</b> (for synchronous consumer).
 *
 */
@Named
@RequestScoped
public class TopicProducerSync extends Producer {
	@Inject
	private Logger logger;

	@Resource(lookup = "jms/StudyTopicSync")
	private Topic topic;

	/**
	 * The constructor.
	 */
	public TopicProducerSync() {
		super();
	}

	/**
	 * Sends the topic messages.
	 * 
	 * @return the result
	 */
	public String sendTopicMessages() {
		sendMessages(topic, "topic");
		if (logger.isLoggable(Level.INFO)) {
			logger.info("sendTopicMessages():");
		}
		return "";
	}
}