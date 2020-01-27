package duke.Exception;

public class MissingTaskNumberError extends Exceptions {
    public MissingTaskNumberError() {
        super(null);
    }

    @Override
    public String errorMessage() {
        return "\nOOPS! I'm sorry, the task number is missing.";
    }
}
