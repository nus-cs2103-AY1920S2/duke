import duke.pack.Command;
import duke.pack.DukeException;
import duke.pack.Parser;
import duke.pack.Storage;
import duke.pack.TaskList;
import duke.pack.Ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents the chatbot
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
    }

    public Duke(String filePath) {
        // following code from module website

        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Starts to run the bot
     */
    public void run() {
        // following code from module website

        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.receiveInput();
                ui.showLine();
                Command comm = parser.parseCommand(command);
                comm.execute(tasks, ui, storage);
                isExit = comm.isExit();

            } catch (DukeException e) {
                ui.showError(e);

            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

}
