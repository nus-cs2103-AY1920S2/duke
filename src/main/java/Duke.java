import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private DukeStorage storage;
    private UserText tasks;
    private DukeParser parser;

    public Duke(String filePath) throws DukeException {
        DukeUI.showWelcomeMessage();
        storage = new DukeStorage(filePath);
        tasks = storage.readText();
        parser = new DukeParser(this.tasks);
    }

    public void run() {
        DukeUI.showWelcomeMessage();
        this.tasks = parser.parseCommand();
        storage.saveTasks(tasks);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
