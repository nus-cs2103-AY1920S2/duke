package views;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.renderDuke(Duke.WELCOME_MESSAGE);
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
        String input = this.userInput.getText();
        
        try {
            Pair output = this.duke.executeInput(input);
            String message = (String) output.getFirstValue();
            boolean shutdown = (boolean) output.getSecondValue();
            
            if (shutdown) {
                this.renderDuke(Duke.GOODBYE_MESSAGE);
                Platform.exit();
            } else {
                this.renderUser(input);
                this.renderDuke(message);
            }
        } catch (BaseException e) {
            this.renderUser(input);
            this.renderDuke(e.getMessage());
        } catch (Exception e) {
            this.renderUser(input);
            this.renderDuke(Duke.UNEXPECTED_ERROR_MESSAGE);
        } finally {
            userInput.clear();
        }
    }

    private void renderUser(String message) {
        this.dialogContainer.getChildren().add(
            ChatBox.getUserDialog(message, this.userImage)
        );
    }

    private void renderDuke(String message) {
        this.dialogContainer.getChildren().add(
            ChatBox.getDukeDialog(message, this.dukeImage)
        );
    }
}