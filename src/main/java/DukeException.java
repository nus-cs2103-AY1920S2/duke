/**
 * CS2103 Individual Project
 * @author Wei Cheng
 * DukeException represents all the potential exceptions
 */
public class DukeException extends IllegalArgumentException {
    /**
     * Constructor
     * @param message Error message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * String representation of the error
     * @return String
     */
    @Override
    public String toString(){
        return getMessage();
    }
}
