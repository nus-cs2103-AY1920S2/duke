package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasks());
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        Ui.printWelcomeMessage();

        Parser.parse(storage, tasks, ui);
    }
}