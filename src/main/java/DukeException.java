/**
 * <h1>Duke Exception Class</h1>
 * simple class to represent duke exceptions
 */

public class DukeException extends Exception {
    /**
     * Method calls standard exception constructor
     * @param error
     */
    public DukeException(String error) {
        super(error);
    }
}
