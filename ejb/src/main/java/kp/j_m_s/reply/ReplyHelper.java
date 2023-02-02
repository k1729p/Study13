package kp.j_m_s.reply;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;

/**
 * The reply helper.
 * <p>
 * Sends the control messages to <b>reply queue</b>.
 */
public class ReplyHelper {

	@Inject
	private List<String> report;

	@Inject
	private JMSContext context;

	/**
	 * The constructor.
	 */
	public ReplyHelper() {
		super();
	}

	/**
	 * Processes the control message.
	 * 
	 * @param message the {@link Message}
	 * @param domain  the domain
	 * @throws JMSException the JMS exception
	 */
	public void processControlMessage(Message message, String domain) throws JMSException {

		final String label = message.getStringProperty("label");
		report.add(String.format("'%s' received CONTROL message, label[%s]", domain, label));

		final Queue replyQueue = (Queue) message.getJMSReplyTo();
		final Message replyMessage = context.createMessage();
		replyMessage.setStringProperty("label", label);
		context.createProducer().send(replyQueue, replyMessage);
		report.add(String.format("'reply queue' sent CONTROL message, label[%s]", label));
	}
}