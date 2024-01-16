package kp.e_j_b.remote;

import jakarta.ejb.Remote;
import kp.e_j_b.Common;

/**
 * The <b>stateful</b> session bean. Extend the {@link Common} interface.
 * <p>
 * This is the <b>remote</b> business interface.
 */
@Remote
public interface StaFu extends Common {
}