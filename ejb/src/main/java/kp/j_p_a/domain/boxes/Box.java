package kp.j_p_a.domain.boxes;

import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * The <B>box</B> entity class.
 *
 */
//SonarQube signals 'duplicated blocks of code'.  But this rule is deprecated in Sonar! 
@MappedSuperclass
public abstract class Box implements Comparable<Box> {
	/*- Mapped superclass inheritance:
	 * 
	 * Mapped superclasses do not have any corresponding tables in the underlying datastore.
	 * Entities that inherit from the mapped superclass define the table mappings.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identifier;

	private String text;

	/**
	 * The constructor.
	 */
	protected Box() {
		super();
	}

	/**
	 * Gets identifier.
	 * 
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * Gets text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets text.
	 * 
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + identifier;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Box other = (Box) obj;
		if (identifier != other.identifier) {
			return false;
		}
		if (text == null) {
			return other.text == null;
		}
		return text.equals(other.text);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public int compareTo(Box box) {

		if (Objects.isNull(box)) {
			return 0;
		}
		final int cmp = (this.identifier - box.getIdentifier());
		return cmp == 0 ? this.text.compareTo(box.text) : cmp;
	}
}