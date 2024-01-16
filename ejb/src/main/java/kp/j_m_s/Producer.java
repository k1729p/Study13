package kp.j_m_s;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.JMSProducer;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import kp.util.Tools;

/**
 * The JMS messages producer base.
 *
 */
public abstract class Producer {
	/*-
	 * A 'jakarta.jms.Destination' object is a JMS administered object.
	 * 
	 * The 'Destination' is the target of messages that the client produces.
	 * The 'Destination' is the source of messages that the client consumes.
	 * 
	 * The 'Destination' is the JMS queue in the point-to-point    messaging domain.
	 * The 'Destination' is the JMS topic in the 'publish/subscribe' messaging domain.
	 */

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private List<String> report;

	/*-
	 * Injected JMSContext is container-managed.
	 * Therefore, these methods must not be used:
	 * 'commit', 'rollback', 'acknowledge', 'start', 'stop', 'recover', 'close'. 
	 */
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private JMSContext context;

	@Resource(lookup = "jms/StudyReplyQueue")
	private Queue replyQueue;

	private static final List<String> MUTABLE_TEXT_LIST = new ArrayList<>(Tools.getTextList());

	/**
	 * The constructor.
	 */
	protected Producer() {
		super();
	}

	/**
	 * Sends messages.
	 * 
	 * @param destination the {@link Destination}
	 * @param domain      the domain (queue or topic)
	 */
	protected void sendMessages(Destination destination, String domain) {

		StringBuilder strBld = new StringBuilder();
		try {
			final JMSProducer producer = context.createProducer();
			for (int i = 1; i <= 3; i++) {
				final String text = MUTABLE_TEXT_LIST.getFirst();
				Collections.rotate(MUTABLE_TEXT_LIST, -1);
				producer.send(destination, text);
				report.add(String.format("'%s' sent message[%s]", domain, text));
				strBld.append(text);
			}
			/*
			 * Send a non-text control message indicating end of messages.
			 */
			final Message controlMessage = context.createMessage();
			final String label = strBld.toString();
			controlMessage.setStringProperty("label", label);
			controlMessage.setJMSReplyTo(replyQueue);
			producer.send(destination, controlMessage);
			report.add(String.format("'%s' sent CONTROL message, label[%s]", domain, label));
		} catch (JMSRuntimeException | JMSException e) {
			logger.severe(String.format("sendMessages(): domain[%s], exception[%s]", domain, e.getMessage()));
		}
	}
}