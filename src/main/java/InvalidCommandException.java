public class InvalidCommandException extends DukeException{
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
