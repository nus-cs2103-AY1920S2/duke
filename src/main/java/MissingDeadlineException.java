class MissingDeadlineException extends InvalidCommandException {
    MissingDeadlineException() {
        super("Oops! The deadline of the task is missing.");
    }
}
