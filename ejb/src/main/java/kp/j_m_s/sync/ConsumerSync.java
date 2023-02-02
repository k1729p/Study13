package kp.j_m_s.sync;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.jms.Destination;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

import kp.j_m_s.reply.ReplyHelper;

/**
 * The <b>synchronous</b> consumer base.
 *
 */
public abstract class ConsumerSync {
	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	@Inject
	private JMSContext context;

	@Inject
	private ReplyHelper replyHelper;

	private static final long RECEIVING_TIMEOUT = 1000;

	/**
	 * The constructor.
	 */
	protected ConsumerSync() {
		super();
	}

	/**
	 * Receives the messages.
	 * 
	 * @param destination the {@link Destination}
	 * @param domain      the domain
	 */
	protected void receiveMessages(Destination destination, String domain) {

		try (final JMSConsumer consumer = context.createConsumer(destination)) {
			boolean loopFlag = true;
			while (loopFlag) {
				final Message message = consumer.receive(RECEIVING_TIMEOUT);
				if (Objects.isNull(message)) {
					continue;
				}
				if (message instanceof TextMessage) {
					report.add(String.format("'%s' received message[%s]", domain, message.getBody(String.class)));
				} else {
					replyHelper.processControlMessage(message, domain);
					loopFlag = false;
				}
			}
		} catch (JMSException e) {
			logger.severe(String.format("receiveMessages(): domain[%s], exception[%s]", domain, e.getMessage()));
		}
	}
}