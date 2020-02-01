
public class Duke {

    private DukeStorage storage;
    private UserText tasks;
    private DukeParser parser;

    public Duke(String filePath) throws DukeException {
        DukeUI.showWelcomeMessage();
        storage = new DukeStorage(filePath);
        tasks = storage.readText();
        parser = new DukeParser(this.tasks);
    }

    public void run() {
        DukeUI.showWelcomeMessage();
        this.tasks = parser.parseCommand();
        storage.saveTasks(tasks);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }
}
