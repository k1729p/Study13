package kp.j_p_a.domain.levels;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

/**
 * The entity class for the <B>first level</B>.
 *
 */
@Entity
public class FirstLevel extends Level {
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy
	private Set<SecondLevel> secondLevels = new TreeSet<>();

	/**
	 * The constructor.
	 */
	public FirstLevel() {
		super();
	}

	/**
	 * Gets the second levels set.
	 * 
	 * @return the second levels set.
	 */
	public Set<SecondLevel> getSecondLevels() {
		return secondLevels;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((secondLevels == null) ? 0 : secondLevels.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FirstLevel other = (FirstLevel) obj;
		if (secondLevels == null) {
			if (other.secondLevels != null)
				return false;
		} else if (!secondLevels.equals(other.secondLevels))
			return false;
		return true;
	}

}