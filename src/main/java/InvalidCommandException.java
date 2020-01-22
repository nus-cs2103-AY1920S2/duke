class InvalidCommandException extends Exception {
    InvalidCommandException() {
        super("Oops! I don't know what that means :(");
    }

    InvalidCommandException(String message) {
        super(message);
    }
}
