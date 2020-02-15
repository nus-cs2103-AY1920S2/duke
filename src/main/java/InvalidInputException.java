public class InvalidInputException extends Exception {
    private String description;

    public InvalidInputException(String message) {
        super(message);
        this.description = message;
    }

    public InvalidInputException() {
    }

    @Override
    public String toString() {
        return this.description;
    }

}
