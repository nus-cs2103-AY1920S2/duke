package duke.main;

import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    Storage storage;
    TaskList taskList;

    /**
     * Duke Object that forms the backbone of the program.
     */
    public Duke() {
        //Try to read form saved data file and restore index, if not create a list to save later
        try {
            String filepath = "data/duke.txt";
            storage = new Storage(filepath);
            taskList = new TaskList(storage);
        } catch (DukeException e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * main Method that begins the program.
     *
     * @param args  are multiple inputs received from User's input
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        }
    }

    /**
     * run Method that executes the actual program.
     *
     * @throws DukeException    when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public void run() throws DukeException {
        //Duke Setup
        boolean dukeRunning = true;

        //Welcome Text
        Ui.welcome();

        //Main Program now in Switch, might need to turn cases into separate methods soon
        while (dukeRunning) {
            String input = Ui.getInput();
            dukeRunning = Parser.parseCommand(input, taskList);
        }

        //Save new data to file before exiting
        storage.save(taskList);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
