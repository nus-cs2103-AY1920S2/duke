package dukebot.command;

public enum CommandList {
    ALIAS("alias"),
    BYE("bye"),
    HELP("help"),
    LIST("list"),
    FIND("find"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

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
