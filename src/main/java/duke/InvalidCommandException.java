package duke;

class InvalidCommandException extends Exception {
    InvalidCommandException() {
        super("Hmm... I don't know what that means :(");
    }

    InvalidCommandException(String message) {
        super(message);
    }
}
