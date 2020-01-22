public class NoDescriptionException extends DukeException {
    @Override
    public String toString() {
        return "A description of the task has to be provided. Please try again.\n";
    }
}
