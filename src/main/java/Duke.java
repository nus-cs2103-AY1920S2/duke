public class Duke {
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath, ui);
        TaskList tasks = new TaskList(storage.load());
        parser = new Parser(tasks, storage, ui);
    }

    public void run() {
        ui.welcomeMessage();
        String input = ui.receiveInput();
        while (parser.parse(input)) {
            input = ui.receiveInput();
        }
        ui.fareWellMessage();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
