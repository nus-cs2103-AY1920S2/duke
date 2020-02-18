import duke.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;



/**
 * Main class for duke.
 */
public class Duke extends Application{

    public Ui ui;
    public Storage storage;
    public TaskList tasks;

    /**
     * Initialize duke.
     */
    public Duke() {
        String filepath = "dukeStorage";
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());

    }

    /**
     * Run duke. Depreciated
     */
    public void run() {
    }

    /**
     * Main Method of duke.
     * @param args takes in command prompt arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command d = Parser.parse(input);
            String reply = d.execute(ui, storage, tasks);
            return reply;
        } catch (DukeException ex) {
            return ex.toString();
        }
    }

    public String startUp() {
        return ui.printLogo() + ui.showWelcome();
    }
}
