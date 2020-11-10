public class EmptyDescriptionException extends DukeException {

    @Override
    public String toString() {
        return "The description of a task cannot be empty.";
    }
}
