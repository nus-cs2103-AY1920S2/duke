// packages imports
import tasks.TaskList;
import ui.Ui;

// java imports
import java.util.Scanner;
import java.io.FileNotFoundException;

// javafx imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class of the chat bot program.
 */
public class Duke extends Application{
    /** User interface class with formatted outputs. */
    private Ui ui;
    /** Allows for persistent data. */
    private Storage storage;
    /** List to store all tasks. */
    private TaskList taskList;

    /** Creates a bot with personalize user interface, storage, and task list.
     * Will create a new save file is there is no existing one.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            storage.readSaveFile(taskList);
        } catch (FileNotFoundException ex) {
            ui.printFormattedOutput("No saved task list found. Creating a new one...");
        }
    }

    /**
     * Main function that abstracts the chat bot's functionalities.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(taskList, storage, ui, sc);

        ui.printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        // Input-Response logic
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            parser.parse(input);
            input = sc.nextLine();
        }

        ui.printFormattedOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Entry point of the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public void start(Stage stage) {
        Label helloWorld = new Label("hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage
    }
}