package duke.pack;

/**
 * Represents an exception for Duke.
 */
public class DukeException extends Exception {
    String s;

    /**
     * Creates an exception specific to Duke.
     * @param s description of exception
     */
    public DukeException(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
