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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke class.
     * 
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

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./tasks.txt");
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

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // sendButton.setOnMouseClicked((event) -> {
        // handleUserInput();
        // });
        //
        // userInput.setOnAction((event) -> {
        // handleUserInput();
        // });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Returns the response by Duke to the given input.
     * @param fullCommand The full input given for the command.
     * @return Response as a String based on the given input.
     */
    public String getResponse(String fullCommand) {
        String response = ui.divider("") + "\n";
        try {
            response += ui.divider("");
            Command c = Parser.parseCommand(fullCommand);
            response += c.execute(tasks, storage, ui);
            storage.updateStorage(tasks);
        } catch (DukeException e) {
            response += ui.showError(e);
        } finally {
            response += "\n" + ui.divider("");
        }
        return response;
    }

    /**
     * Executes the main function of Duke.
     */
    public void run() {
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
        new Duke("./tasks.txt").run();
    }

}
