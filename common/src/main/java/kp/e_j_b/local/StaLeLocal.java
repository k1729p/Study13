package kp.e_j_b.local;

import jakarta.ejb.Local;
import kp.e_j_b.Common;

/**
 * Represents a <b>stateless</b> session bean that extends the {@link Common} interface.
 * <p>
 * This is the local business interface annotated with {@link Local}.
 * </p>
 */
@Local
public interface StaLeLocal extends Common {
}