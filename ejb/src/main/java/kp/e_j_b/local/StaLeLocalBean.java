package kp.e_j_b.local;

import java.io.Serializable;

import jakarta.ejb.Stateless;

import kp.e_j_b.CommonImpl;

/**
 * The implementation of the <b>stateless</b> session bean interface
 * {@link StaLeLocal}.
 * <p>
 * This is the <b>local</b> view enterprise bean.
 */
@Stateless
public class StaLeLocalBean extends CommonImpl implements StaLeLocal, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 */
	public StaLeLocalBean() {
		super();
	}
}