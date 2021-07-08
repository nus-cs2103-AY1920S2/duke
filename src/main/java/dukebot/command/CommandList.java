package dukebot.command;

/**
 * List of commands.
 */
public enum CommandList {
    ADD_CONTACT("add_contact"),
    ALIAS("alias"),
    BYE("bye"),
    CONTACTS("contacts"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DELETE_CONTACT("delete_contact"),
    DONE("done"),
    EVENT("event"),
    FIND("find"),
    HELP("help"),
    LIST("list"),
    RESCHEDULE("reschedule"),
    RESET("reset"),
    TODO("todo");

    private final String defaultCommand;

    CommandList(String defaultCommand) {
        this.defaultCommand = defaultCommand;
    }

    /**
     * Gets the default command.
     *
     * @return The default string which refers to the command.
     */
    public String getDefaultCommand() {
        return defaultCommand;
    }
}
