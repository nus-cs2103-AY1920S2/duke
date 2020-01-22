public class InvalidCommandException extends DukeException {
    @Override
    public String toString() {
        return "The command entered is invalid. Please try again.\n";
    }
}
