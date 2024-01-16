package kp.j_m_s.async;

import java.util.List;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import kp.j_m_s.reply.ReplyHelper;

/**
 * The <b>asynchronous</b> consumer base.
 *
 */
public abstract class ConsumerAsync {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private List<String> report;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private ReplyHelper replyHelper;

	/**
	 * The constructor.
	 */
	protected ConsumerAsync() {
		super();
	}

	/**
	 * Processes messages.
	 * 
	 * @param message the {@link Message}
	 * @param domain  the domain
	 */
	protected void process(Message message, String domain) {

		try {
			if (message instanceof TextMessage) {
				report.add(String.format("'%s' received message[%s]", domain, message.getBody(String.class)));
			} else {
				replyHelper.processControlMessage(message, domain);
			}
		} catch (JMSException e) {
			logger.severe(String.format("process(): JMSException[%s]", e.getMessage()));
		}
	}
}