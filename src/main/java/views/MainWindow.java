package views;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import app.Duke;
import app.core.Messages;
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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Janet.jpg"));

    // -----------------------------------------------------------------------------------------
    public void setDuke(Duke duke) {
        this.duke = duke;
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

    // -----------------------------------------------------------------------------------------
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.renderDuke(Messages.WELCOME_MESSAGE);
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String output = "";
        boolean shutdown = false;
        
        try {
            Pair result = this.duke.executeInput(input);
            output = (String) result.getFirstValue();
            shutdown = (boolean) result.getSecondValue();
        } catch (BaseException e) {
            output = e.getMessage();
        } catch (Exception e) {
            output = Messages.UNEXPECTED_ERROR_MESSAGE;
        } finally {
            this.renderUser(input);
            this.renderDuke(output);
            userInput.clear();

            if (shutdown) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    Platform.exit();
                    System.exit(0);
                });
                pause.play();
            }
        }
    }
}