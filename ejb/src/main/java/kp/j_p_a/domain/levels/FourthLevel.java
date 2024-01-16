package kp.j_p_a.domain.levels;

import jakarta.persistence.Entity;

/**
 * The entity class for the <B>fourth level</B>.
 *
 */
@Entity
public class FourthLevel extends Level {
	/**
	 * The constructor.
	 */
	public FourthLevel() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		return getClass() == obj.getClass();
	}

}