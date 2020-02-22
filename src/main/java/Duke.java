
public class Duke {

    private DukeParser parser;

    public Duke() {
        String filePath = "tasks.txt";
        DukeStorage storage = new DukeStorage(filePath);
        TaskList tasks = storage.readText();
        parser = new DukeParser(tasks);
    }

    public String getResponse(String input) {
        return parser.parseCommand(input);
    }
}
