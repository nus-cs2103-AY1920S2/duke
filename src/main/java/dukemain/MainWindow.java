package dukemain;

import dukecommand.HelpCommand;
import dukeui.DukeUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    private DukeUI dui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Nezuko.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Shinobu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dui = new DukeUI();
        String welcomeMessage = dui.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * Essentially a modified form of print onto the dialogContainer
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void sendHelpMessageButton() {
        HelpCommand hc = new HelpCommand();
        String helpOutput = hc.getHelpString();

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(helpOutput, dukeImage)
        );
    }
}
