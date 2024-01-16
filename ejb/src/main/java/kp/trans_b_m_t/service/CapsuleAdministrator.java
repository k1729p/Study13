package kp.trans_b_m_t.service;

import java.io.Serializable;

import jakarta.ejb.Local;

/**
 * The capsule administrator.
 * <p>
 * This is the <b>local</b> business interface.
 */
@Local
public interface CapsuleAdministrator extends Serializable {
	/**
	 * Creates capsule.
	 */
	void create();

	/**
	 * Reads the capsule.
	 */
	void read();

	/**
	 * Deletes the capsule.
	 */
	void delete();

	/**
	 * Commits the transaction.
	 */
	void commit();

	/**
	 * Rollbacks the transaction.
	 */
	void rollback();
}