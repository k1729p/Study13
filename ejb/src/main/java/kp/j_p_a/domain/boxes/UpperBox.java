package kp.j_p_a.domain.boxes;

import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

/**
 * The <B>upper box</B> entity class.
 *
 */
//SonarQube signals 'duplicated blocks of code'.  But this rule is deprecated in Sonar! 
@Entity
public class UpperBox extends Box {

	// the inverse side of bidirectional relationship
	@OneToMany(mappedBy = "upperBox")
	@OrderBy
	private Set<CentralBox> centralBoxes = new TreeSet<>();

	/**
	 * The constructor.
	 */
	public UpperBox() {
		super();
	}

	/**
	 * Adds central box.
	 * 
	 * @param centralBox the central box to add
	 */
	public void addCentralBox(CentralBox centralBox) {

		centralBoxes.add(centralBox);
		centralBox.setUpperBox(this);
	}

	/**
	 * Removes central box.
	 * 
	 * @param centralBox the central box to remove
	 */
	public void removeCentralBox(CentralBox centralBox) {

		centralBoxes.remove(centralBox);
		centralBox.setUpperBox(null);
	}

	/**
	 * Gets central boxes.
	 * 
	 * @return the central boxes set
	 */
	public Set<CentralBox> getCentralBoxes() {
		return centralBoxes;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((centralBoxes == null) ? 0 : centralBoxes.hashCode());
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
		UpperBox other = (UpperBox) obj;
		if (centralBoxes == null) {
			if (other.centralBoxes != null)
				return false;
		} else if (!centralBoxes.equals(other.centralBoxes))
			return false;
		return true;
	}

}