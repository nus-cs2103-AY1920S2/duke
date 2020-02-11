package dukeproj;

import dukeproj.command.Command;
import dukeproj.command.ListCommand;
import dukeproj.enums.SayType;
import dukeproj.exception.InvalidCommandException;
import dukeproj.gui.DialogBox;
import javafx.application.Application;
import javafx.application.Platform;
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

import java.io.File;

/**
 * Represents the GUI Application which handles the user's input and Duke's response.
 */
public class GuiApp extends Application {
    /** A Local duke variable that contains all the quintessential duke data structures. */
    private Duke duke;
    /** Handles the creation and modification of the scroll pane in the GUI. */
    private ScrollPane scrollPane;
    /** Represents the dialog container in the GUI. */
    private VBox dialogContainer;
    /** Represents the user's input box inside the GUI. */
    private TextField userInput;
    /** Stores the user's image. */
    private Image userIm = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** Stores Duke's image. */
    private Image dukeIm = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Starts the GUI application. Required method from Application abstract class.
     *
     * @param stage The stage that the GUI will run on.
     */
    @Override
    public void start(Stage stage) {
        // Set up components
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

        // Format window
        stage.setTitle("I AM DUKE");
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

        // Add functionality to handle user input.
        sendButton.setOnMouseClicked((event -> {
            handleUserInput();
        }));
        userInput.setOnAction((event -> {
            handleUserInput();
        }));
        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable -> {
            scrollPane.setVvalue(1.0);
        }));

        displayDukeResponse(duke.getUi().say(SayType.INTRO));
    }

    /**
     * Echoes a string into the GUI by Duke.
     *
     * @param str String to be echoed into the GUI by Duke.
     */
    public void displayDukeResponse(String str) {
        Label text = new Label(str);

        text.setWrapText(true);

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(text, new ImageView(dukeIm)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and appends them to
     * dialog container. Clears user input upon processing.
     * The method will also check whether the command given by user is an exit command and responses appropriately.
     */
    private void handleUserInput() {
        StringBuilder dukeResponse = new StringBuilder();
        Command command = getDukeCommand(dukeResponse);
        assert dukeResponse.length() > 0 : "Response cannot be empty";
        //set up labels for user and duke
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(dukeResponse.toString());
        userText.setWrapText(true);
        dukeText.setWrapText(true);
        //add labels into dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userIm)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeIm))
        );
        if (command.isExit()) {
            closeApp();
        }
        userInput.clear();
    }

    private Command getDukeCommand(StringBuilder response) {
        try {
            Command command = duke.getCommandResponse(userInput.getText());
            response.append(duke.getResponse(command));
            return command;
        } catch (InvalidCommandException e) {
            response.append(duke.getUi().say(SayType.INVALID_COMMAND));
            return new ListCommand();
        }
    }

    /**
     * Closes the GUI Application.
     */
    private void closeApp() {
        try {
            Platform.exit();
        } catch (Exception e) {
            displayDukeResponse("Oh dear! I cant seem to close, please alt+F4");
        }
    }

    /**
     * Constructs a GUI app with a predetermined Duke variable.
     */
    public GuiApp() {
        duke = new Duke("." + File.separator + "data" + File.separator + "Task.txt");
    }

}
