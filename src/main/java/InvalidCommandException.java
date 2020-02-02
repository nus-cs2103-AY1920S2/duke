public class InvalidCommandException extends DukeException{
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Uhh... You're gonna have to say that again, Red.";
    }
}
