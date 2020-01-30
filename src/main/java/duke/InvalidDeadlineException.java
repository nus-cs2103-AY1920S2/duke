package duke;

class InvalidDeadlineException extends InvalidCommandException {
    InvalidDeadlineException() {
        super("I don't understand the deadline. Please provide it yyyy-mm-dd format.");
    }
}
