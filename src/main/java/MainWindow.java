import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

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
    private Gui gui = new Gui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/NewUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/IntroDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(gui.printStartUp(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response.substring(0, 2).equals(":(")) {
            dukeImage = new Image(this.getClass().getResourceAsStream("/images/Fail.png"));
        } else {
            dukeImage = new Image(this.getClass().getResourceAsStream("/images/NewDuke.png"));
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals("Bye. Hope to see you again soon!\n")) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            }, 1000);
        }
    }

}
