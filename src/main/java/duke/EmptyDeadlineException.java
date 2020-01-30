package duke;

class EmptyDeadlineException extends EmptyTaskException {
    EmptyDeadlineException() {
        super("Oops! The description of a deadline cannot be empty.");
    }
}
