class MissingTimeException extends InvalidCommandException {
    MissingTimeException() {
        super("Oops! The time of the event is missing.");
    }
}
