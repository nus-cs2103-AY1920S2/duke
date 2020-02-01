package duke;

import duke.command.Command;
import duke.gui.DialogParser;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Driver for duke project.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    protected TaskList tasks;
    private Ui ui;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Returns a new Duke instance, uses duke.txt for save file.
     */
    public Duke() {
        this("duke.txt");
    }

    /**
     * Return a new instance of Duke class.
     *
     * @param saveFile File name used for saving user data
     */
    public Duke(String saveFile) {
        ui = new Ui();
        storage = new Storage(saveFile);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // Did not load tasks from save file
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run(reader);
    }

    /**
     * Process input given by user and execute relevant actions.
     *
     * @param reader used for user input
     */
    public void run(BufferedReader reader) {
        ui.greet();
        boolean requestExit = false;
        while (!requestExit) {
            // Run process command, check if user has terminated program
            try {
                Command c = Parser.parse(ui.readCommand(reader));
                c.execute(tasks, ui, storage);
                requestExit = c.isExit();
            } catch (IOException ioException) {
                ui.unableToReadUserInput();
            } catch (DukeException dukeException) {
                // Print error message
                ui.showExceptionMessage(dukeException);
            }
        }
        ui.goodbye();
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // The container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        // Formatting the window to look as expected
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400.0, 600.0);
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        scrollPane.setPrefSize(400.0, 550.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        // Set user input and send button size
        userInput.setPrefWidth(330.0);
        userInput.setPrefHeight(45.0);
        sendButton.setPrefWidth(60.0);
        sendButton.setPrefHeight(45.0);
        // Place scroll pane at top
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        // Place send button at bottom right
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        // Place user input at bottom left
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        // Add nodes to layout
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Add functionality to handle user input
        sendButton.setOnMouseClicked((event -> {
            DialogParser.handleUserInput(this, this.dialogContainer, this.userInput);
        }));

        userInput.setOnAction((event -> {
            DialogParser.handleUserInput(this, this.dialogContainer, this.userInput);
        }));
    }
}
