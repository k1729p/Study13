package kp.j_p_a.domain.boxes;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

/**
 * The <b>Box</b> class annotated with {@link MappedSuperclass}.
 * <p>
 * Mapped superclass inheritance:
 * </p>
 * <ul>
 * <li>Mapped superclasses do not have any corresponding tables in the underlying datastore.</li>
 * <li>Entities that inherit from the mapped superclass define the table mappings.</li>
 * </ul>
 */
//SonarQube signals 'duplicated blocks of code'.  But this rule is deprecated in Sonar! 
@MappedSuperclass
public abstract class Box implements Comparable<Box> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifier;
    private String text;

    /**
     * Default constructor.
     */
    protected Box() {
        // constructor is empty
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * {@inheritDoc}
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
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Box other = (Box) obj;
        return identifier == other.identifier && Objects.equals(text, other.text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Box box) {

        if (Objects.isNull(box)) {
            return 0;
        }
        int cmp = Integer.compare(this.identifier, box.getIdentifier());
        return cmp == 0 ? this.text.compareTo(box.text) : cmp;
    }
}