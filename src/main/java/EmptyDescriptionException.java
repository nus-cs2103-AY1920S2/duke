public class EmptyDescriptionException extends DukeException {
    @Override
    public String toString() {
        return "Description of a task cannot be empty!";
    }
}
