public class DukeException extends Exception {
    public static String exceptionIcon = "\u2639";
    /**
     * Used to construct a new exception related to Duke class
     * @param message to represent more information about exception
     */
    public DukeException(String message) {
        super(message);
    }
}
