package duke.main;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a Duke program that stores user-inputted tasks.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Duke class.
     * @param filePath Filepath of the storage file tasks.txt
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.buildTaskList();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
            storage.updateStorage(tasks);
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");
        try {
            tasks = storage.buildTaskList();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
            storage.updateStorage(tasks);
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }


    /**
     * Executes the main function of Duke.
     */
    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.divider("");
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, storage, ui);
                storage.updateStorage(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.divider("");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
