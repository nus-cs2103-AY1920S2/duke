package duke;

class MissingDeadlineException extends InvalidCommandException {
    MissingDeadlineException() {
        super("Oh no! The deadline of the task is missing.");
    }
}
