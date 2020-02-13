package duke;

/**
 * Represents the type of command entered by the user.
 */
public enum CommandType {
    LIST_CMD("list"),
    DONE_CMD("done"),
    DELETE_CMD("delete"),
    TODO_CMD("todo"),
    DEADLINE_CMD("deadline"),
    EVENT_CMD("event"),
    FIND_CMD("find"),
    HELP_CMD("help"),
    BYE_CMD("bye");

    private String name;

    CommandType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
