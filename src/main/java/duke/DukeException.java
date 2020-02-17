package duke;

/**
 * Represents most of the exception generated when user give
 * wrong input format or invalid command.
 *
 * @author Kenny Ho
 */
public class DukeException extends Exception {

    protected DukeErrorType errorType;
    protected String commandName;

    /**
     * Constructor for duke.DukeException class. Used if error message should include command
     * name to be printed.
     *
     * @param message     String object which represents the message user want to display without using toString method.
     * @param errorType   An Enum class categorising which error type encountered.
     * @param commandName duke.command.Command name of the command given by user.
     */
    public DukeException(String message, DukeErrorType errorType, String commandName) {
        super(message);
        this.errorType = errorType;
        this.commandName = commandName;
    }

    /**
     * Another constructor for duke.DukeException class. Used if error message do not require
     * command name to be printed
     *
     * @param message   String object which represents the message user want to display without using toString method.
     * @param errorType An Enum class categorising which error type encountered.
     */
    public DukeException(String message, DukeErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * Returns a short description of this throwable.
     * The result is the concatenation of:
     * <ul>
     * <li> the {@linkplain Class#getName() name} of the class of this object
     * <li> ": " (a colon and a space)
     * <li> the result of invoking this object's {@link #getLocalizedMessage}
     *      method
     * </ul>
     * If {@code getLocalizedMessage} returns {@code null}, then just
     * the class name is returned.
     *
     * @return a string representation of this throwable.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        switch (errorType) {
        case INVALID_COMMAND:
            stringBuilder.append(":( Small peepee move, please give valid command!");
            break;
        case EMPTY_DESCRIPTION:
            stringBuilder.append(":( WHOOPS I did it again~\n      ");
            stringBuilder.append(commandName);
            stringBuilder.append("description is empty!\n");
            stringBuilder.append("Describe it for my smol brain please :)");
            break;
        case EMPTY_TIME:
            stringBuilder.append("Date and time?\n" );
            stringBuilder.append(commandName);
            stringBuilder.append("time is empty!\nSet it for your smol brain please :)");
            break;
        case INVALID_ABBREVIATION:
            stringBuilder.append("Invalid abbreviation in data document, please check!\n");
            stringBuilder.append("Only T/D/E abbreviation is allowed.");
            break;
        case EMPTY_COMMAND:
            stringBuilder.append("Please give me something to exe :D");
            break;
        case INVALID_FILE_CONTENT:
            stringBuilder.append("Please make sure file content is separated with |");
            break;
        }
        return stringBuilder.toString();
    }
}