package kp.e_j_b.remote;

import jakarta.ejb.Remote;
import kp.e_j_b.Common;

/**
 * The <b>stateful</b> session bean. Extends the {@link Common} interface.
 * <p>
 * This is the remote business interface annotated with {@link Remote}.
 * </p>
 */
@Remote
public interface StaFu extends Common {
}