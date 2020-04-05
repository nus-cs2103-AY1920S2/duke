package sharadhr.duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sharadhr.duke.Duke;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * <p>
 * 90% lifted from iP JavaFX FXML tutorial; changes made to accommodate
 * interacting with the Duke controller.
 *
 * @author Jeffry Lum
 */
public class MainWindow extends AnchorPane {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Duke.output.helloString(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput()
    {
        String input = userInput.getText();

        // Redirects user input in the text field to stdin.
        duke.redirect(input);

        String response = duke.getResponse();

        DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);

        DialogBox dukeDialogBox = DialogBox.getDukeDialog(response, dukeImage);

        dialogContainer.getChildren().addAll(userDialogBox, dukeDialogBox);
        userInput.clear();
    }

}