import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import dukebot.command.Command;
import dukebot.command.Parser;
import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;
import dukebot.gui.DialogBox;

/**
 * Main class.
 */
public class Duke{
    private static final String PATH = "./dukeStore.txt";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user_default.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke_happy.png"));

    /**
     * Main method.
     */
    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }



    private void run() {
        Storage storage = new Storage(PATH);
        Ui ui = new Ui();
        ui.showWelcome();

        TaskList tasks;
        try {
            ArrayList<Task> taskArrayList = storage.loadFromFile();
            tasks = new TaskList(taskArrayList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
            ArrayList<Task> taskArrayList = new ArrayList<>();
            tasks = new TaskList(taskArrayList);
            try {
                storage.saveToFile(tasks);
            } catch (DukeException g) {
                ui.sayLine(g.getErrorLineName());
            }
        }

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        System.exit(0);
    }
}