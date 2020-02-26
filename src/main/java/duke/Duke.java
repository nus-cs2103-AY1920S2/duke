package duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *duke.Main class.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String file = "duke.txt";

    /**
     * Public no-argument constructor for Launcher to work.
     */
    public Duke() {
    }

    /**
     * Takes in path of file to be read.
     * Initializes UI, Storage and TaskList classes.
     *
     * @param filePath of stored data
     */
    private void loadDuke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs duke, loads and stores tasks.
     *
     * @param input of user
     * @return response of Duke
     */
    public String runDuke(String input) {
        try {
            String response = "";
            storage = new Storage(file);
            tasks = new TaskList(storage.load());
            Parser parser = new Parser(input, tasks);
            response = parser.readCommand();
            storage.store(parser.taskList);

            assert !response.equals("") : "Response should not be empty";
            return response;

        } catch (Exception e) {
            return e.toString();
        }

    }

    @Override
    public void start(Stage stage) {
    }

    /**
     * Generates Duke response.
     */
    public String getResponse(String input) {
        String response = runDuke(input);
        return response;
    }

}