package duke.exception;

/**
 * InvalidCommandException
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class InvalidCommandException extends DukeException {
    @Override
    public String toString() {
        return "The command entered is invalid. Please try again.\n";
    }
}
