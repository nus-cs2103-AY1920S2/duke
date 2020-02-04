public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     * @param filePath Pathname of file where Duke tasks are being stored at
     */
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
        Duke newDuke = new Duke("data/tasks.txt");
        newDuke.run();
    }
}
