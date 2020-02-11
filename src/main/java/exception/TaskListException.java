package exception;

public class TaskListException extends DukeException {
    public TaskListException() {
        super(
                String.format(
                        "it appears you've made a happy accident. Your list is empty!"));
    }

    public TaskListException(int index) {
        super(
                String.format(
                        "Happy accident! Please choose an index that is between 1 and %d (inclusive)",
                        index));
    }
}
