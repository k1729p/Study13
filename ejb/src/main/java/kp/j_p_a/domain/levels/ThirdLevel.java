package kp.j_p_a.domain.levels;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

/**
 * The entity class for the <B>third level</B>.
 *
 */
@Entity
public class ThirdLevel extends Level {
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy
	private Set<FourthLevel> fourthLevels = new TreeSet<>();

	/**
	 * The constructor.
	 */
	public ThirdLevel() {
		super();
	}

	/**
	 * Gets the fourth levels set.
	 * 
	 * @return the fourth levels set.
	 */
	public Set<FourthLevel> getFourthLevels() {
		return fourthLevels;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fourthLevels == null) ? 0 : fourthLevels.hashCode());
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
		ThirdLevel other = (ThirdLevel) obj;
		if (fourthLevels == null) {
			if (other.fourthLevels != null)
				return false;
		} else if (!fourthLevels.equals(other.fourthLevels))
			return false;
		return true;
	}

}