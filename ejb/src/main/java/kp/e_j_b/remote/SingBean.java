package kp.e_j_b.remote;

import java.io.Serial;
import java.io.Serializable;

import jakarta.ejb.Singleton;
import kp.e_j_b.CommonImpl;

/**
 * The implementation of the <b>singleton</b> session bean interface
 * {@link Sing}.
 * <p>
 * This is the <b>remote</b> view enterprise bean.
 */
@Singleton
public class SingBean extends CommonImpl implements Sing, Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 */
	public SingBean() {
		super();
	}
}