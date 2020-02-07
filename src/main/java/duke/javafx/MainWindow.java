package duke.javafx;

import duke.duke.Duke;
import duke.exception.DukeException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Controller for duke.JavaFx.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    Duke duke = new Duke();
    private int counter = 1;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DukeJavaFxRunner dukeJavaFxRunner;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Trump.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Putin.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog("Sup Putin, my best Friend", userImage));
    }

    public void setDukeJavaFxRunner(DukeJavaFxRunner d) {
        dukeJavaFxRunner = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    // The main place where the code will be.
    @FXML
    void handleUserInput() {

        String input = userInput.getText();
        String response = "";
        // Gets the response from the fxrunner thing we set.
        try {
            response = duke.run(input);
        } catch (Exception e) {
            response = "Please enter todo/deadline/event/list. The program will close if "
                    + "you enter " + (3 - counter) + " more wrong format";
            counter++;

            if (counter > 3) {
                response = "You entered too many incorrect formats, the program will now close";
                counter = 1;
                Platform.exit();
            }

        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

    }
}