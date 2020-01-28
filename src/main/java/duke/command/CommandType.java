package duke.command;

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

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
