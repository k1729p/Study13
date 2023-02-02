package kp.trans_b_m_t.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The capsule.
 *
 */
@Entity
public class Capsule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String text;

	/**
	 * The constructor.
	 * 
	 */
	public Capsule() {
		super();
	}

	/**
	 * The constructor.
	 * 
	 * @param text the text
	 */
	public Capsule(String text) {
		super();
		this.text = text;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
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
}
