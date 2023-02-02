package kp.client;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import kp.Constants;
import kp.e_j_b.remote.Sing;
import kp.e_j_b.remote.StaFu;
import kp.e_j_b.remote.StaLe;

/**
 * The application for WildFly Application Client.
 *
 */
public class ClientApplication {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	@EJB
	private static StaLe staLe;

	@EJB
	private static StaFu staFu;

	@EJB
	private static Sing sing;

	private static final String LINE_SEP = System.getProperty("line.separator");
	private static final String FROM_LOOKUP_STAMP = "from lookup";
	private static final String FROM_EJB_STAMP = "from @EJB";

	/**
	 * The constructor.
	 */
	public ClientApplication() {
		super();
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		final StringBuilder strBld = new StringBuilder();
		strBld.append(Constants.BREAK);
		strBld.append(staLe.check(FROM_EJB_STAMP)).append(LINE_SEP);
		/*- 
		   When tried with 'StaLeLocalBean' got EJBException:
		   "Server error (invalid view): EJB view is not remote".
		*/
		final StaLe staLeFromLookup = (StaLe) jndiLookup("StaLeBean");
		strBld.append(staLeFromLookup.check(FROM_LOOKUP_STAMP)).append(LINE_SEP);
		strBld.append(staFu.check(FROM_EJB_STAMP)).append(LINE_SEP);
		strBld.append(sing.check(FROM_EJB_STAMP)).append(Constants.BREAK);
		if (logger.isLoggable(Level.INFO)) {
			logger.info(strBld.toString());
		}
	}

	/**
	 * The JNDI lookup.
	 * 
	 * @param name the name of the object to look up
	 * @return the object bound to name
	 */
	private static Object jndiLookup(String name) {

		Object object = null;
		/*- java : global / <app-name> / <module-name> / <bean-name> ! <fully-qualified-interface-name> */
		try {
			object = new InitialContext().lookup(String.format("java:global/Study13/Study13_ejb/%s", name));
		} catch (NamingException e) {
			logger.severe("jndiLookup(): NamingException[" + e.getMessage() + "]");
			System.exit(1);
		}
		return object;
	}
}