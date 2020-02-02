package dukebot.command;

public enum CommandList {
    ALIAS("alias"),
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    FIND("find"),
    HELP("help"),
    LIST("list"),
    RESET("reset"),
    TODO("todo");

    private final String defaultCommand;

    CommandList(String defaultCommand) {
        this.defaultCommand = defaultCommand;
    }

    /**
     * Gets the default command.
     */
    public String getDefaultCommand() {
        return defaultCommand;
    }
}
