package duke;

import duke.commands.Command;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.exceptions.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Scanner;

/**
 * The Main class used to run the application.
 * Creates the Ui, Storage and TaskList objects.
 * Catches DukeExceptions and prints the error messages.
 * Specifies the path to tasks.txt.
 * Terminates when execute method of Command returns false.
 */
public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Pikachu.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Squirtle.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        try {
            ui = new Ui();
            storage = new Storage();
            taskList = new TaskList(storage.load());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Welcome to Manager SQUIRTLE's platform!");
            alert.setHeaderText("SQUIRTLE is here to help!");
            alert.setContentText("type 'help' to see what SQUIRTLE can do!");

            alert.showAndWait();
        } catch (DukeException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Error in loading file!");
            alert.setContentText("SQUIRTLE cannot load your data from file!! Sorry!!");
            alert.showAndWait();
        }


        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Go!");
        sendButton.setStyle("-fx-background-color: skyblue; -fx-text-fill: white;");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.setFill(Color.SKYBLUE); // add colour to the top bar
        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("SQUIRTLE Manager");
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

        Label welcomeMsg = getDialogLabel(ui.start()); // print the welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMsg, new ImageView(duke)));

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = getDialogLabel(userInput.getText());
        Label dukeText = getDialogLabel(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            Command cmd = Parser.parseInput(input);
            return cmd.execute(storage, taskList, ui);
        } catch (DukeException e) {
            return ui.errorMsg(e);
        }
    }

}
