package exceptions;

public class ToDoException extends DukeException {

    /**
     * Constructor for ToDoException
     *
     * @param message any message to convey
     */
    public ToDoException(String message) {
        super(message);
    }

    public String toString() {
        return ExceptionsConstant.FORMAT_LINE
                + "\nPlease enter another todo command, this time, with "
                + "a known command word/valid number;\n"
                + "For example, 'todo good'\n"
                + ExceptionsConstant.FORMAT_LINE;
    }

}
