package duke;

public class NoValueProvidedException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    /**
     * Called when the user did not fully provide value to create an object.
     *
     * @param message the message
     */
    public NoValueProvidedException(String message) {
        super(message);
    }
}
