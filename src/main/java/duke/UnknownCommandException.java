package duke;

/**
 * Class for unknown commands.
 */
public class UnknownCommandException extends DukeException {
    /**
     * toString method.
     * @return the toString method
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
