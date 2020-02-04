package duke;

class TaskNumberOutOfBoundsException extends DukeException {
    TaskNumberOutOfBoundsException(int taskNumber) {
        super("Oops! " + taskNumber + " doesn't correspond to any task.");
    }
}
