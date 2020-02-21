package duke.exception;

/**
 * Indicates that an index is out of bound
 */
public class OutOfBoundMarkingRequestException extends Exception {

    /**
     * Constructor with an index number
     * @param num
     */
    public OutOfBoundMarkingRequestException(int num) {
        super(String.format("Position %d is out of bound!", num));
    }
}
