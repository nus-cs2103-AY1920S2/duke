package duke.main;

import duke.exception.CannotSaveFileException;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.DialogBox;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Duke extends Application {
    Storage storage;
    TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText.toString(), new ImageView(user).getImage()),
            DialogBox.getDukeDialog(dukeText.toString(), new ImageView(duke).getImage())
        );
        userInput.clear();
    }

    /**
     * getResponse performs the tasks as necessary, and returns response to userInput.
     */
    public String getResponse(String input) {
        String output = Parser.parseCommand(input, taskList);

        if (output.equals("BYE")) {
            return exitDuke(taskList);
        } else {
            return output;
        }
    }

    //Custom exitDuke Method to handle data saving and timed termination
    private String exitDuke(TaskList taskList) {
        //Save new data to file before exiting
        try {
            storage.save(taskList);
        } catch (CannotSaveFileException e) {
            return e.toString();
        }

        //Timed exit to terminate the program while allowing you to see the Bye Message
        Executors.newSingleThreadScheduledExecutor()
            .schedule(() -> System.exit(0), 2, TimeUnit.SECONDS);

        return ("Bye. Hope to see you again soon!");
    }
}
