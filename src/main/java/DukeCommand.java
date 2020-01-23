enum DukeCommand {

    END_COMMAND         ("bye"),
    LIST_COMMAND        ("list"),
    DONE_COMMAND        ("done"),
    TODO_COMMAND        ("todo"),
    DELETE_COMMAND      ("delete"),

    DEADLINE_COMMAND    ("deadline"),
    DEADLINE_BY         ("/by"),

    EVENT_COMMAND       ("event"),
    EVENT_AT            ("/at");

    private final String command;

    DukeCommand(String command) {
        this.command = command;
    }

    String getCommand() {
        return command;
    }
}
