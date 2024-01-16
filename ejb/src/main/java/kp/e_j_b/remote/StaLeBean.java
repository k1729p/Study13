package kp.e_j_b.remote;

import java.io.Serial;
import java.io.Serializable;

import jakarta.ejb.Stateless;
import kp.e_j_b.CommonImpl;

/**
 * The implementation of the <b>stateless</b> session bean interface
 * {@link StaLe}.
 * <p>
 * This is the <b>remote</b> view enterprise bean.
 */
@Stateless
public class StaLeBean extends CommonImpl implements StaLe, Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 */
	public StaLeBean() {
		super();
	}
}