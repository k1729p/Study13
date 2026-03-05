package kp.e_j_b;

/**
 * Common interface for other remote and local view interfaces.
 */
public interface Common {
    /**
     * Checks the bean implementation.
     * <p>
     * This is a business method.
     * </p>
     *
     * @param stamp the stamp
     * @return the message
     */
    String check(String stamp);

    /**
     * Changes the value of the session bean field.
     * <p>
     * This is a business method.
     * </p>
     *
     * @param stamp  the stamp
     * @param number the number
     * @return the message
     */
    String change(String stamp, int number);
}
