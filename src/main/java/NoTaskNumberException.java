public class NoTaskNumberException extends DukeException {
    @Override
    public String toString() {
        return "Task number of task to be marked or deleted has to be provided. Please try again.\n";
    }
}
