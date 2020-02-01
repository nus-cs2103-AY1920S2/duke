import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class representation of Duke
 */
public class Duke extends Application{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke Constructor
     * @param filePath
     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }

    public Duke() {
        String filePath = "/Users/gerrenseow/Documents/Gerren/MODULES/Y2S2/CS2103T/Individual_Project/duke/src/main/java/data/data.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     *  Duke's run method
     *  Calls its Ui attribute, and activates the onStart function
     */
    public void run() {
        ui.onStart(tasks, storage.filepath);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
