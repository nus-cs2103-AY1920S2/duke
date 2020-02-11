package duke;

import javafx.application.Application;
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
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Duke provides a todo list functionality.
 */
public class Duke /*extends Application*/ {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * No params constructor for Launcher class to initialize Duke.
     */
    public Duke() {

    }

    /**
     * Constructor for Duke.
     * @param filePath provide a hardcoded directory path to the text file to be used as a database
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception when loading database");
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the todo list.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand); // throws DukeException
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getErrorMessage());
            } finally {
                ui.showLine();
            }
        }

        // saving tasks
        try {
            ui.showSavingTasks();
            ui.showLine();
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("error saving tasks");
            e.printStackTrace();
        }
    }

    //package-private
    String getResponse(String input) {
        return "Duke's response: " + input;
    }
}