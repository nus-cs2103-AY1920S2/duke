enum DukeCommand {

    bye         ("bye"),
    list        ("list"),
    done        ("done"),
    todo        ("todo"),
    delete      ("delete"),

    deadline    ("deadline"),
    deadline_by ("/by"),

    event       ("event"),
    event_at    ("/at");

    private final String command;

    DukeCommand(String command) {
        this.command = command;
    }

    String getCommand() {
        return command;
    }
}
