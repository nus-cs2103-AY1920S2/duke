import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Creates an instance of storage, ui, tasklist and parser in the main.
 * Starts running the program.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initialises variables and loads data.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.readFile(), ui);
            parser = new Parser(ui, tasks, storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(ui);
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

    public String greeting() {
        return ui.sayHi();
    }


}
