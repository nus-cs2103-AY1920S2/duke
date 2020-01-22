public class InvalidIndexException extends DukeException {
    int size;

    InvalidIndexException(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Index supplied is invalid. Current task list has " + size + (size < 2 ? "task." : "tasks.") + " Please try again.\n";
    }
}
