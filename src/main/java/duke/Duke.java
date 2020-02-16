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

    /**
     * Public no-argument constructor for Launcher to work.
     */
    public Duke() {
    }

    /**
     * Constructor that takes in path of file to be read.
     * Initializes UI, Storage and TaskList classes.
     *
     * @param filePath
     */
    private void loadDuke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs duke, loads and stores tasks.
     *
     * @param input
     * @return
     */
    public String runDuke(String input) {
        try {
            String response = "";
            storage = new Storage("duke.txt");
            tasks = new TaskList(storage.load());
            Parser parser = new Parser(input, tasks);
            response = parser.readCommand();
            storage.store(parser.taskList);

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