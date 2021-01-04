import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    @FXML
    private TextField dukeWelcome;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, DukeException, InterruptedException {
        String input = userInput.getText();
        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        if (duke.getGoodbye()) {
            close();
        }
    }

    /**
     * Initialises the message that Duke displays when the user opens the application.
     */
    public void initialiseMsg() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getUi().welcome(), dukeImage)
        );
    }

    /**
     * Closes the Duke application launcher.
     */
    private void close() {

        //@@author lohszeying-reused
        //Reused from https://stackoverflow.com/questions/52602990/why-cant-we-call-threadsleep-directly-inside-a-lambda-function with minor modification
        Thread t1 = new Thread() {
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                Platform.exit();
                System.exit(0);
            }
        };
        t1.start();
        //@author
    }
}