package duke;

class InvalidTaskException extends InvalidCommandException {
    InvalidTaskException() {
        super("Hmm... I don't know which task you mean :(");
    }

    InvalidTaskException(String message) {
        super(message);
    }
}
