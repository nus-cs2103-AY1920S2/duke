package duke;

public enum CommandType {
    LIST_CMD("list"),
    DONE_CMD("done"),
    DELETE_CMD("delete"),
    TODO_CMD("todo"),
    DEADLINE_CMD("deadline"),
    EVENT_CMD("event"),
    BYE_CMD("bye");

    private String name;

    private CommandType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
