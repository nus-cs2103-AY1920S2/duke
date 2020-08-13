package duke.exceptions;

/**
 * Handles event where commands to manipulate list have no index provided.
 */
public class DukeNoIndexException extends DukeException {

    /**
     * Constructor for DukeNoIndexException.
     * @param command string indicating command to be used in custom exception message.
     */
    public DukeNoIndexException(String command) {
        if (command.equals("delete")) {
            super.message = "You forgot to tell me what to delete!";
        } else {
            super.message = "You forgot to tell me what to mark as done!!";
        }
    }
}
