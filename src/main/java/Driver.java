import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;
import duke.util.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/*
 * Driver
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The Driver class is the class that contains the
 * main method to receive inputs from the client and
 * passing it to Duke to be processed.</p>
 * @author Mario Lorenzo
 */

public class Driver extends Application {

    /**
     * The main method runs the program.
     * @param args The command line arguments entered into the program.
     */

    public static void main(String[] args) {
        Ui userInterface = new Ui();
        try {
            Duke duke = Duke.start();
            userInterface.start();

            String command = userInterface.readCommand();
            userInterface.printLine();
            while (!command.equals("bye")) {
                String message = duke.processCommand(command);
                userInterface.displayMessage(message);
                userInterface.printLine();
                command = userInterface.readCommand();
            }

        } catch (DukeInvalidTaskFormatException | DukeInvalidDateFormatException e) {
            userInterface.displayErrorMessage(e);
            userInterface.printLine();
        } finally {
            userInterface.close();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
