package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.Serializer;
import duke.io.Ui;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    public Duke() {
        ui = new Ui(System.in);
        tasks = new TaskList(Serializer.deserialize());
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.printWelcomeMessage();

        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);

                if (command != null) {
                    command.execute(tasks, ui);
                    shouldExit = command.isExitCommand();
                }

            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
