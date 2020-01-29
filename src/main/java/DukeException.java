/**
 * Represents exceptions that Duke throws.
 */
public class DukeException extends Exception{
    public DukeException() {}

    public DukeException(String message) {
        super(message);
    }
    
    @Override
    public String toString() {
        return this.getMessage();
    }
}
