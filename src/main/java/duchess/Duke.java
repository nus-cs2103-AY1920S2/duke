package duchess;

import duchess.command.Command;
import duchess.exception.DuchessException;
import duchess.io.Parser;
import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The {@code Duke} class is the entry point of the Duchess program.
 * Upon initialising an instance of this class, calling run() on it
 * will begin the program.
 *
 * <p>To initialise the {@code Duke} instance, a {@code String filePath}
 * ending with {@code .json} needs to be passed into the constructor.
 * If no file is found at the provided file path, a brand new JSON file will
 * be created.
 *
 * @author Zhu Hanming
 */
public class Duke extends Application {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Initialises a newly created {@code Duchess} object that uses the
     * provided {@code filePath} as the location of the JSON save file.
     *
     * @param filePath A {@code String} denoting the location of the JSON
     *                 save file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DuchessException e) {
            this.ui.printLoadingError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Initialises Duke with the default storage location.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.json");
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DuchessException e) {
            this.ui.printLoadingError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Begins the Duchess program. Upon calling the run() method, the user
     * can begin to interact with the program.
     */
    public void run() {
        this.ui.printWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command command = Parser.parse(fullCommand);
                command.execute.apply(fullCommand, this.taskList, this.ui, this.storage);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (DuchessException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Starts up the Duchess program.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Duke duchess = new Duke("data/tasks.json");
        duchess.run();
    }

    /**
     * Starts the JavaFX Stage.
     *
     * @param stage Stage to show the screen.
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        HBox.setHgrow(userInput, Priority.ALWAYS);
        VBox mainLayout = new VBox();
        HBox inputLayout = new HBox();
        inputLayout.getChildren().addAll(userInput, sendButton);
        mainLayout.getChildren().addAll(scrollPane, inputLayout);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        sendButton.setPrefWidth(55.0);
        stage.show();
    }
}
