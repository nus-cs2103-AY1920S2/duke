package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidCommandException;

/**
 * Duke class represents the main chatbot entity; it contains classes that manage different components of the chatbot.
 */
public class Duke extends Application {
    /** the path where the saved Tasklist file is stored. */
    private static final Path filePath = Paths.get(System.getProperty("user.dir"), "data.duke");
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Adapted from JavaFX tutorial.
     * @param stage this is the primary stage that JavaFX provides.
     */
    @Override
    public void start(Stage stage) {
        // Creating a new Label to contain the text that we want to show.
        Label helloWorld = new Label("Hello World!"); 
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Initializes Duke and loads the TaskList from the files in the save directory.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(storage, ui, tasks);
        try {
            storage.loadFromFile(tasks);
            System.out.println("    Tasklist loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("    No prior tasklist found...");
        } catch (IOException e) {
            System.out.println("    Existing tasklist cannot be read...");
        } catch (ClassNotFoundException e) {
            System.out.println("    Existing tasklist cannot be read...");
        }
    }

    /** Starts Duke and begins reading commands from input. */
    private void run() {       
        ui.out("Hello! I'm Duke", "What can I do for you?");
        boolean isShutdown = false;
        while (!isShutdown) {
            String line = ui.getLine();
            try {
                isShutdown = parser.parse(line);
                storage.saveToFile(tasks);
            } catch (InvalidCommandException | IncorrectArgumentException e) {
                ui.out(e.getMessage());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
