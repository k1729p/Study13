package kp.e_j_b.local;

import jakarta.ejb.Local;

import kp.e_j_b.Common;

/**
 * The <b>stateless</b> session bean. Extend the {@link Common} interface.
 * <p>
 * This is the <b>local</b> business interface.
 */
@Local
public interface StaLeLocal extends Common {
}