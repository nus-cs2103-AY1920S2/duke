package duke.exception;

public class OutOfBoundMarkingRequestException extends Exception {
    public OutOfBoundMarkingRequestException(int num) {
        super(String.format("Position %d is out of bound!", num));
    }
}
