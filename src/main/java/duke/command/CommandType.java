package duke.command;

/**
 * <h1>Command Type Enum</h1>
 * This enum stores different commands that Duke can understand.
 * It able to give the command type in String format.
 *
 * @author Eng Xuan En
 */
public enum CommandType {
    DELETE("delete"),
    DONE("done"),
    BYE("bye"),
    LIST("list"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    FIND("find");

    private final String command;

    /**
     * Constructor of the enum CommandType which pair the string to its respective enum constant.
     *
     * @param command value to pair with the CommandType
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Get the command type in String format.
     *
     * @return the string value of the command
     */
    public String getCommand() {
        return command;
    }
}
