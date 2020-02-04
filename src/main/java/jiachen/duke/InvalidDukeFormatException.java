package jiachen.duke;

/**
 * Throws wehn bad format is encountered with input
 */
public class InvalidDukeFormatException extends DukeException {
    /**
     * Instantiates a new Invalid duke format exception.
     *
     * @param errMsg the err msg
     */
    public InvalidDukeFormatException(String errMsg) {
        super(errMsg);
    }
}
