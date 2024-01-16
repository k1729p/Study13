package kp.j_p_a.domain.components;

/**
 * The <b>cardinal direction</b> enumeration.
 *
 */
public enum CardinalDirection {
	/**
	 * The north direction.
	 */
	NORTH,
	/**
	 * The east direction.
	 */
	EAST,
	/**
	 * The south direction.
	 */
	SOUTH,
	/**
	 * The west direction.
	 */
	WEST;

	/**
	 * Gets the next cardinal direction.
	 * 
	 * @return the next cardinal direction
	 */
	public CardinalDirection getNext() {

		return switch (this) {
		case NORTH -> EAST;
		case EAST -> SOUTH;
		case SOUTH -> WEST;
		default -> NORTH;
		};
	}
}
