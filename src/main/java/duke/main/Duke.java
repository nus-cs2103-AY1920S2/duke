package duke.main;

import duke.exception.DukeException;
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

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    Storage storage;
    TaskList taskList;

    /**
     * Duke Object that forms the backbone of the program.
     */
    public Duke() {
        //Try to read form saved data file and restore index, if not create a list to save later
        try {
            String filepath = "data/duke.txt";
            storage = new Storage(filepath);
            taskList = new TaskList(storage);
        } catch (DukeException e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * main Method that begins the program.
     *
     * @param args  are multiple inputs received from User's input
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        }
    }

    /**
     * run Method that executes the actual program.
     *
     * @throws DukeException    when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public void run() throws DukeException {
        //Duke Setup
        boolean dukeRunning = true;

        //Welcome Text
        Ui.welcome();

        //Main Program now in Switch, might need to turn cases into separate methods soon
        while (dukeRunning) {
            String input = Ui.getInput();
            dukeRunning = Parser.parseCommand(input, taskList);
        }

        //Save new data to file before exiting
        storage.save(taskList);
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

        //Step 2. Formatting the window to look as expected
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
    }
}
