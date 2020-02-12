package dukeproj.gui;

import dukeproj.Duke;
import dukeproj.command.Command;
import dukeproj.command.ListCommand;
import dukeproj.enums.SayType;
import dukeproj.exception.InvalidCommandException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kaguya.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Gintoki.png"));
    private Image backImage = new Image(this.getClass().getResourceAsStream("/Images/Background.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        BackgroundImage backgroundImage = new BackgroundImage(backImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        scrollPane.getStylesheets().add(
                this.getClass().getResource("/css/ScrollPaneStyles.css").toString());
        setBackground(background);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Echoes a string into the GUI by Duke.
     *
     * @param str String to be echoed into the GUI by Duke.
     */
    public void displayDukeResponse(String str) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(str, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and appends them to
     * dialog container. Clears user input upon processing.
     * The method will also check whether the command given by user is an exit command and responses appropriately.
     */
    @FXML
    private void handleUserInput() {
        StringBuilder dukeResponse = new StringBuilder();
        Command command = getDukeCommand(dukeResponse);
        assert dukeResponse.length() > 0 : "Duke response cannot be empty";
        //set up labels for user and duke
        String userText = userInput.getText();
        String dukeText = dukeResponse.toString();
        //add input and response into dialog container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
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


}
