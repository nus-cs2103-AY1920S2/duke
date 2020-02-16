package duke.main;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Main application class for a To Do List Application
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Storage object used to store TaskList object into a Storage mechanism (e.g. File)
     */
    private Storage storage;

    /**
     * TaskList object used to store Tasks
     */
    private TaskList taskList;

    /**
     * Constructor for Duke class
     */
    public Duke() {
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Function to run Duke object
     */
    public void run() {
        Ui.start();
        boolean run = true;
        while (run) {
            try {
                run = Parser.parseCommand(Ui.getInput(), taskList);
            } catch (DukeException ex) {
                Ui.printException(ex);
            }
        }
    }



    /**
     * Function to run Duke object through GUI
     * @param input String input from GUI
     */
    public void runCommand(String input) {
        try {
            boolean run = Parser.parseCommand(input, taskList);
            if (!run) {
                Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), 2, TimeUnit.SECONDS);
            }
        } catch (DukeException ex) {
            Ui.printException(ex);
        }
    }

    /**
     * Default function to be invoked
     *
     * @param args Optional String command line arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        this.runCommand(input);
        return Ui.getBuffer();
    }
}
