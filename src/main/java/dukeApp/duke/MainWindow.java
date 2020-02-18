package dukeApp.duke;

import dukeApp.files.DukeException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Controller for dukeApp.duke.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        BackgroundFill background_fill = new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        dialogContainer.setBackground(background);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing dukeApp.duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates the Pusheen dialog box to display the welcome message when the app is first started
     * @param msg welcome message
     */
    public void startScreen(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(msg, dukeImage)
        );
    }
}
