package seedu.duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeIoException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Construct a chatbot instance with saved data at default path.
     */
    public Duke() {
        this(Paths.get(
                System.getProperty("user.home"),
                "code",
                "duke",
                "data",
                "duke.txt"));
    }

    /**
     * Construct a chatbot instance with saved data specified by the file path argument.
     *
     * @param filePath Path of type Path that specify location of saved data.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeIoException e) {
            ui.print("can't load file.");
            tasks = new TaskList();
        }
    }

    /**
     * Main method that instantiate an instance and invoke run method.
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Read in user command until exit command is fed.
     */
    public void run() {
        ui.greet();

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
