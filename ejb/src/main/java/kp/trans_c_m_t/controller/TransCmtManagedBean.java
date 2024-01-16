package kp.trans_c_m_t.controller;

import static kp.trans_c_m_t.queues.ParcelQueueQualifier.Type.CONFIRM_CREATE;
import static kp.trans_c_m_t.queues.ParcelQueueQualifier.Type.CREATE;
import static kp.trans_c_m_t.queues.ParcelQueueQualifier.Type.READ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import kp.trans_c_m_t.queues.ParcelQueueQualifier;
import kp.trans_c_m_t.service.AuditorBean;
import kp.util.Tools;

/**
 * The CDI managed bean for the <b>container-managed</b> transactions research.
 *
 */
@Named
@RequestScoped
public class TransCmtManagedBean {
	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private Logger logger;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private JMSContext context;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	@ParcelQueueQualifier(CREATE)
	private Queue createParcelQueue;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	@ParcelQueueQualifier(CONFIRM_CREATE)
	private Queue confirmCreateParcelQueue;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	@ParcelQueueQualifier(READ)
	private Queue readParcelQueue;

	@SuppressWarnings("java:S6813") // switch off Sonarqube rule 'Avoid field dependency injection'
	@Inject
	private AuditorBean auditorBean;

	private static final List<String> MUTABLE_TEXT_LIST = new ArrayList<>(Tools.getTextList());

	/**
	 * The constructor.
	 */
	public TransCmtManagedBean() {
		super();
	}

	/**
	 * Creates the parcel.
	 * 
	 * @return the result
	 */
	public String create() {

		final Message message = context.createTextMessage(MUTABLE_TEXT_LIST.getFirst());
		try {
			message.setJMSReplyTo(confirmCreateParcelQueue);
		} catch (JMSException e) {
			logger.severe(String.format("create(): exception[%s]", e.getMessage()));
			return "";
		}
		context.createProducer().send(createParcelQueue, message);
		Collections.rotate(MUTABLE_TEXT_LIST, -1);
		if (logger.isLoggable(Level.INFO)) {
			logger.info("create():");
		}
		return "";
	}

	/**
	 * Reads the parcel.
	 * 
	 * @return the result
	 */
	public String read() {
		context.createProducer().send(readParcelQueue, context.createMessage());
		if (logger.isLoggable(Level.INFO)) {
			logger.info("read():");
		}
		return "";
	}

	/**
	 * Shows the audit.
	 * 
	 * @return the result
	 */
	public String showAudit() {
		auditorBean.showAudit();
		if (logger.isLoggable(Level.INFO)) {
			logger.info("showAudit():");
		}
		return "";
	}
}