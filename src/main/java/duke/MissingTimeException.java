package duke;

class MissingTimeException extends InvalidCommandException {
    MissingTimeException() {
        super("Oh no! The time of the event is missing.");
    }
}
