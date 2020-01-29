/**
 * NoDescriptionException
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class NoDescriptionException extends DukeException {
    @Override
    public String toString() {
        return "A description of the task has to be provided. Please try again.\n";
    }
}
