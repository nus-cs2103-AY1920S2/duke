import grapie.exceptions.GrapieExceptions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser_nobg.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke_nobg.png"));

    @FXML
    public void initialize() throws IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetings(), dukeImage));
    }

    /**
     * Returns greetings to user.
     */
    public static String greetings() {
        String intro = "Hello! I'm Grapie! \n"
                + "   _____                 _      \n"
                + "  / ____|               (_)     \n"
                + " | |  __ _ __ __ _ _ __  _  ___ \n"
                + " | | |_ | '__/ _` | '_ \\| |/ _ \\ \n"
                + " | |__| | | | (_| | |_) | |  __/ \n"
                + "  \\_____|_|  \\__,_| .__/|_|\\___| \n"
                + "                  | |           \n"
                + "                  |_|           \n"


                + "What do ya need from me?\n"
                + "---------------------------\n";

        return intro;
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, GrapieExceptions {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}