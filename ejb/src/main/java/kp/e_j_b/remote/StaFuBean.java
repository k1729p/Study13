package kp.e_j_b.remote;

import java.io.Serializable;

import jakarta.ejb.Stateful;

import kp.e_j_b.CommonImpl;

/**
 * The implementation of the <b>stateful</b> session bean interface
 * {@link StaFu}.
 * <p>
 * This is the <b>remote</b> view enterprise bean.
 */
@Stateful
public class StaFuBean extends CommonImpl implements StaFu, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 */
	public StaFuBean() {
		super();
	}
}