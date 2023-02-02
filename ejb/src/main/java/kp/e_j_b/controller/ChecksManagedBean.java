package kp.e_j_b.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import kp.Constants;
import kp.e_j_b.local.NoIntViBean;
import kp.e_j_b.local.StaLeLocal;
import kp.e_j_b.remote.Sing;
import kp.e_j_b.remote.StaFu;
import kp.e_j_b.remote.StaLe;

/**
 * The CDI <b>managed</b> bean for the EJBs checks.
 *
 */
@Named
@RequestScoped
public class ChecksManagedBean {
	/*-
	As from JSF 2.3 onwards '@ManagedBean' (bean managed by JSF container) is being phased out and
	the recommended approach is '@Named' (CDI bean managed by application server).
	*/

	@Inject
	private Logger logger;

	@Inject
	private List<String> report;

	@EJB
	private NoIntViBean noIntViBean;
	@EJB
	private NoIntViBean noIntViBeanToCompare;
	@Inject
	private NoIntViBean noIntViBeanInjected;
	@Inject
	private NoIntViBean noIntViBeanInjectedToCompare;

	@EJB
	private StaLeLocal staLeLocal;
	@EJB
	private StaLeLocal staLeLocalToCompare;
	@Inject
	private StaLeLocal staLeLocalInjected;
	@Inject
	private StaLeLocal staLeLocalInjectedToCompare;

	/*-
	You cannot inject an EJB using its remote interface unless you define a resource.
	It raises DeploymentException "Unsatisfied dependencies" when using annotation @Inject:
	 - with stateless bean remote view
	 - with stateful  bean remote view
	 - with singleton  bean
	 */
	@EJB
	private StaLe staLe;
	@EJB
	private StaLe staLeToCompare;

	@EJB
	private StaFu staFu;
	@EJB
	private StaFu staFuToCompare;

	@EJB
	private Sing sing;
	@EJB
	private Sing singToCompare;

	private static final String FROM_EJB_STAMP = "from @EJB";
	private static final String FROM_INJECT_STAMP = "from @Inject";

	/**
	 * The constructor.
	 */
	public ChecksManagedBean() {
		super();
	}

	/**
	 * Researches stateless, stateful, and singleton beans.
	 * 
	 * @return the result
	 */
	public String researchStatelessStatefulSingleton() {

		report.add(noIntViBean.check(FROM_EJB_STAMP));
		report.add(staLeLocal.check(FROM_EJB_STAMP));
		report.add(staLe.check(FROM_EJB_STAMP));
		report.add(staFu.check(FROM_EJB_STAMP));
		report.add(sing.check(FROM_EJB_STAMP));
		compareBeansFromEjb();
		report.add(Constants.LINE);

		report.add(noIntViBeanInjected.check(FROM_INJECT_STAMP));
		report.add(staLeLocalInjected.check(FROM_INJECT_STAMP));
		compareBeansFromInject();
		compareBeans();
		report.add(Constants.LINE);
		if (logger.isLoggable(Level.INFO)) {
			logger.info("researchStatelessStatefulSingleton():");
		}
		return "";
	}

	/**
	 * Compares beans from '@EJB' annotation.
	 * 
	 */
	private void compareBeansFromEjb() {

		String msg = String.format(
				"Compare two beans from '@EJB' injection: no-interface view[%b], stateless local view[%b], "
						+ "stateless remote view[%b], singleton remote view[%b]",
				noIntViBean.equals(noIntViBeanToCompare), staLeLocal.equals(staLeLocalToCompare),
				staLe.equals(staLeToCompare), sing.equals(singToCompare));
		report.add(msg);

		StaLeLocal staLeLocalFromLookup = null;
		StaLe staLeFromLookup = null;
		try {
			final InitialContext context = new InitialContext();
			staLeLocalFromLookup = (StaLeLocal) context.lookup("java:global/Study13/Study13_ejb/StaLeLocalBean");
			staLeFromLookup = (StaLe) context.lookup("java:global/Study13/Study13_ejb/StaLeBean");
		} catch (NamingException e) {
			logger.severe(String.format("compareBeansFromEjb(): exception[%s]", e.getMessage()));
			return;
		}
		msg = String.format(
				"Compare bean from lookup vs bean from '@EJB' injection: stateless local view[%b], "
						+ "stateless remote view[%b]",
				staLeLocalFromLookup.equals(staLeLocalToCompare), staLeFromLookup.equals(staLeToCompare));
		report.add(msg);

		msg = String.format("Compare two beans from '@EJB' injection: stateful remote view[%b]",
				staFu.equals(staFuToCompare));
		report.add(msg);
	}

	/**
	 * Compares beans from '@Inject' annotation.
	 * 
	 */
	private void compareBeansFromInject() {

		final String msg = String.format(
				"Compare two beans from '@Inject' injection: no-interface view[%b], stateless local view[%b]",
				noIntViBeanInjected.equals(noIntViBeanInjectedToCompare),
				staLeLocalInjected.equals(staLeLocalInjectedToCompare));
		report.add(msg);
	}

	/**
	 * Compares beans from '@EJB' and from '@Inject' annotations.
	 * 
	 */
	private void compareBeans() {

		final String msg = String.format(
				"Compare bean from '@EJB' injection vs bean from '@Inject' injection: "
						+ "no-interface view[%b], stateless local view[%b]",
				noIntViBean.equals(noIntViBeanInjected), staLeLocal.equals(staLeLocalInjected));
		report.add(msg);
	}
}