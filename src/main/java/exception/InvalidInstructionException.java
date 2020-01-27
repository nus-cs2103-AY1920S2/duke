package exception;

public class InvalidInstructionException extends RuntimeException {
    public InvalidInstructionException(String message) {
        super((message));
    }
}
