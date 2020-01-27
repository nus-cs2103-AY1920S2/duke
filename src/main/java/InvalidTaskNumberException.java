class InvalidTaskNumberException extends InvalidTaskException {
    InvalidTaskNumberException(int invalidTaskNumber) {
        super("Oops! " + invalidTaskNumber + " is not a valid task number.");
    }
}
