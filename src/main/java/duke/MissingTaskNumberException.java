package duke;

class MissingTaskNumberException extends InvalidCommandException {
    MissingTaskNumberException() {
        super("Oh no! The task number is missing.");
    }
}
