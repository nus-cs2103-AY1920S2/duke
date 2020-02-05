import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     * @param filePath Pathname of file where Duke tasks are being stored at
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
        ui.setTaskList(tasks);
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt"); //default filepath
        tasks = new TaskList(storage);
        ui.setTaskList(tasks);
    }

    public void run() {
        ui.awaitUserInput();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        Duke newDuke = new Duke("data/tasks.txt");
        newDuke.run();
    }

}
