import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialize the GUI.
     */
    @FXML
    public void initialize() throws IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(this.welcomeMsg(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
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


    /**
     * Loads welcome message from text file and prints it out in GUI.
     * @return the Welcome message when Duke is first launched.
     */
    public static String welcomeMsg() throws IOException {
        String msg = "";
        msg += "Hello! I am Peachy, your personal work manager!\n"
                + "Here is what I can do for you:\n"
                + "\n—————Task Manager—————\n"
                + "To create a new:\n"
                + "1. todo: todo description\n"
                + "2. event: event description /at YYYY-MM-DD HHmm\n"
                + "3. deadline: deadline description /by YYYY-MM-DD HHmm\n"

                + "\n—————Note Manager—————\n"
                + "To create a new note/add to existing note: note type_of_note description\n"

                + "\n—————Other Commands—————\n"
                + "1. list: List out all your current tasks\n"
                + "2. done (integer): Mark task numbered integer as done e.g. done 1\n"
                + "3. delete (integer): Delete task numbered integer e.g. delete 3\n"
                + "4. find (keyword): Find tasks with keyword e.g. find book\n"
                + "5. search YYYY-MM-DD: Find tasks by date e.g. search 2020-02-03\n"
                + "6. view all notes: View all your current note\n"
                + "7. view type_of_note notes: Shows the content of your type_of_note notes\n"
                + "8. help: this message will be shown";

        return msg;
    }

}