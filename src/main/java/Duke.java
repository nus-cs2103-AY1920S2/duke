import java.io.IOException;
import java.time.DateTimeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * Represents a Personal Assistant Chatbot named EXE that
 * helps a person to keep track of various things.
 *
 * @author Kenny Ho
 * @since 2020-01-20
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
     * A constructor to initialise Storage, TaskList and Ui class
     * which is responsible for loading and saving tasks, containing
     * the task list with additional operations and
     * interaction with the user respectively.
     *
     * @param filePath Relative path of the storage text file used.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        } catch (IOException io) {
            ui.showStorageError();
        }
    }

    public Duke() {
        this("data" + System.getProperty("file.separator") + "duke.txt");
    }

    /**
     * Execute the start-up, message shown, and main functions of the chatbot
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command currentCommand = Parser.parse(fullCommand);
                currentCommand.execute(tasks, ui, storage);
                isExit = currentCommand.isExit();
            } catch (DukeException dukeEx) {
                ui.showStandardError(dukeEx);
            } catch (DateTimeException dateEx) {
                ui.showDateTimeException();
            } catch (IndexOutOfBoundsException indexEx) {
                ui.showIndexOutOfBoundException(tasks.getSize());
            }
        }
    }

    /**
     * Create a Duke object which is used to invoke start-up of Chatbot.
     *
     * @param args Unused.
     */

    public static void main(String[] args) {
        new Duke("data" + System.getProperty("file.separator") + "duke.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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
//
//        Scene anotherScene = new Scene(new Label("Another scene here"));
//        Stage anotherStage = new Stage();
//        anotherStage.setScene(anotherScene);
//        anotherStage.show();
    }
}