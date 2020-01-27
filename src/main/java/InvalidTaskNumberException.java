class InvalidTaskNumberException extends InvalidTaskException {
    InvalidTaskNumberException(int invalidTaskNumber) {
        super("Oops! " + invalidTaskNumber + " doesn't correspond to any task.");
    }
}
