package duke;

/**
 * Main class for running the Duke program.
 */
public class Duke {
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        Storage storage = new Storage("data/tasks.txt", ui);
        TaskList tasks = new TaskList(storage.load());
        parser = new Parser(tasks, storage, ui);
    }

    /**
     * Initializes the Duke program.
     *
     * @param filePath Location of the output file.
     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        Storage storage = new Storage(filePath, ui);
//        TaskList tasks = new TaskList(storage.load());
//        parser = new Parser(tasks, storage, ui);
//    }

    /**
     * Runs the Duke program and starts to receive inputs from user.
     */
//    public void run() {
//        ui.welcomeMessage();
//        String input = ui.receiveInput();
//        while (parser.parse(input)) {
//            input = ui.receiveInput();
//        }
//        ui.fareWellMessage();
//        ui.close();
//    }

    public String getResponse(String input) {
        String response;
        try {
            response = parser.parse(input);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    public String getWelcomeMessage() {
        return ui.welcomeMessage();
    }

//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }
}
