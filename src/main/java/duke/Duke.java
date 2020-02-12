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
public class Duke {
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

    //package-private
    String getResponse(String command) {
        String response;

        try {
            Command c = Parser.parse(command);
            response = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            e.printStackTrace();
            return e.getErrorMessage();
        }

        //seems inefficient to write to file after every command
        //TODO: implement saving only upon exit
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("error saving tasks");
            e.printStackTrace();
            return e.getMessage();
        }

        return response;
    }
}