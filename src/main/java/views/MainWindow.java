package views;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import app.Duke;
import app.exceptions.BaseException;
import app.util.Pair;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow {
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
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        
        try {
            Pair output = this.duke.executeInput(input);
            String message = (String) output.getFirstValue();
            boolean shutdown = (boolean) output.getSecondValue();
            
            if (shutdown) {
                Platform.exit();
            } else {
                this.render(input, message);
            }
        } catch (BaseException e) {
            this.render(input, e.getMessage());
        } catch (Exception e) {
            this.render(input, Duke.UNEXPECTED_ERROR_MESSAGE);
        } finally {
            userInput.clear();
        }
    }

    private void render(String input, String message) {
        this.dialogContainer.getChildren().addAll(
            ChatBox.getUserDialog(input, this.userImage),
            ChatBox.getDukeDialog(message, this.dukeImage)
        );
    }
}