package duke;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;
import duke.commands.Command;
import duke.exception.DukeException;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Duke chatbot that helps a person to keep track of various things.
     * @param filePath path to file that stores a list of user's tasks.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(this.storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            File file = new File("data");
            if (!file.exists()) {
                //create new data dir and duke.file
                new File("data").mkdir();
            }
            new File(filePath).createNewFile();
            tasks = new TaskList();
        }
    }

    /**
     * Creates a Duke chatbot that helps a person to keep track of various things.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}