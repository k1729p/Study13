package kp.j_p_a.domain.units;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The entity class for the <B>side</B>.
 *
 */
@Entity
public class Side {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	/**
	 * The constructor.
	 */
	public Side() {
		super();
	}
}
