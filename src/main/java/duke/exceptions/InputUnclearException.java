package duke.exceptions;

/**
 * Exception when inputs of user are unclear, prompting for clearer input.
 */
public class InputUnclearException extends DukeException {

    /**
     * Constructor for InputUnclearException.
     *
     * @param message any message to convey.
     */
    public InputUnclearException(String message) {
        super(message);
    }

    /**
     * Provides a custom String representation of the current Exception, which includes formatting lines.
     *
     * @return the presentable String representation.
     */
    @Override
    public String toString() {
        return "I'm not quite sure I understand you :(\n"
                + "Please enter another command, this time, with "
                + "a known command word/valid number;\n"
                + "You can try typing 'help' if you're unsure!";
    }

}
