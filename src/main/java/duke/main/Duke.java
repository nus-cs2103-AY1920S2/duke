package duke.main;

import java.io.IOException;
import javafx.application.Platform;


public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for duke.main.Duke to start the initialise the necessary variables.
     */
    public Duke() {
        storage = new Storage();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        ui = new Ui(tasks);
    }

    /**
     * Takes in the user input to be parsed and processed by Parse to get an output.
     * While this happens, the storages gets updated too
     * @param input from the user
     * @return the generated output by Duke
     * @throws IOException when stored file cannot be read or accessed
     */
    public String getResponse(String input) throws IOException {

        if (input.equals("bye")) {
            Platform.exit();
        }

        Parser parser = new Parser();
        String output = parser.parse(input, ui, tasks);
        storage.updateData(tasks);
        return output;
    }

}




