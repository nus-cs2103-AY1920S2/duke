import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents a Duke chat bot, which supports basic todo list features such as adding and deleting tasks,
 * marking a task as done or list out all the tasks.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Constructs a Duke chat bot instance.
     * @param filePath the file path of the data file in which the existing tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = storage.loadTasks();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    /**
     * Starts the chat bot, exits when encountering the "bye" command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser();
                Command c = parser.parse(fullCommand, tasklist);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}