public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
            tasks = new TaskList(storage);
            ui.setTaskList(tasks);
    }

    public void run() {
        ui.awaitUserInput();
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
