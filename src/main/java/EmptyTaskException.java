class EmptyTaskException extends InvalidCommandException {
    EmptyTaskException() {
        super("Oops! The description of a task cannot be empty.");
    }

    EmptyTaskException(String message) {
        super(message);
    }
}
