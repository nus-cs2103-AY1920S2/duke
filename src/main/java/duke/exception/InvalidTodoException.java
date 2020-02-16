package duke.exception;

/**
 * Thrown when user inputs invalid to do command.
 */
public class InvalidTodoException extends InvalidFormatException {
    public InvalidTodoException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\n" + "For todo commands, follow this format: todo {intended task}";
    }
}
