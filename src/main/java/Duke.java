/**
 * Duke is a friendly chatbot who will keep track of your tasks
 * and whether they have been completed.
 */
public class Duke {

    private static final String filePath = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {}

    public String init() {
        ui = new Ui();
        storage = new Storage(filePath);
        String response = ui.getWelcome();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            response = ui.getLoadingError();
            tasks = new TaskList();
        }
        return response;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        boolean isExit;
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (isExit) {
                System.exit(0);
            }
        } catch (DukeException e) {
            response = ui.getError(e.getMessage());
        }
        return response;
    }
}
