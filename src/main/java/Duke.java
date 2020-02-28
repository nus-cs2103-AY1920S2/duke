
public class Duke {

    private DukeParser parser;
    private DukeStorage storage;
    private TaskList tasks;

    /**
     * Duke constructor.
     */
    public Duke() {
        String filePath = "tasks.txt";
        storage = new DukeStorage(filePath);
        tasks = storage.readText();
        parser = new DukeParser(tasks);
    }

    public void saveData() {
        storage.saveTasks(tasks);
    }

    public String getResponse(String input) {
        return parser.parseCommand(input);
    }
}
