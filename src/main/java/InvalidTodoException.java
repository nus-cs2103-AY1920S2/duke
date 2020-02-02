public class InvalidTodoException extends DukeException {
    public InvalidTodoException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "You're usually not that taciturn, Red. Explain things to me.";
    }
}
