package kp.j_m_s.async.topic;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.Topic;

import kp.j_m_s.Producer;

/**
 * JMS messages <b>topic producer</b> (for asynchronous consumer).
 *
 */
@Named
@RequestScoped
public class TopicProducerAsync extends Producer {
	@Inject
	private Logger logger;

	@Resource(lookup = "jms/StudyTopicAsync")
	private Topic topic;

	/**
	 * The constructor.
	 */
	public TopicProducerAsync() {
		super();
	}

	/**
	 * Sends topic messages.
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