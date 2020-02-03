import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents a command that adds item to list. A <code>AddCommand</code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"deadline", "read /by 2019-05-10 1800"</code>
 */
public class Duke extends Application{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Constructor for Duke object.
     * @param filePath path of file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to run the bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("Data/duke.txt");
        new Duke(path.toString()).run();
    }
}