import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private Button exitButton;

    private Duke duke = new Duke ("src/main/java/data/duke.txt");

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Penguin.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Santa.png"));

    @FXML
    public void initialize() throws IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        duke.load();
        String openingPage = "Welcome to Polar Express:)" +
                "\nI am your personal task assistant!" +
                "\nIf this is your first time here," +
                "\ntype 'commands' to see what I can do for you!";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(openingPage, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception {
        Parser parser = new Parser (duke.getTaskList());
        String input = userInput.getText();
        String response = parser.parse (input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Closes the application and saves data into text file
     *
     * @throws IOException if unable to find location to save file
     */
    @FXML
    private void exitAndSave() throws IOException {
        duke.save();
        Platform.exit();
    }
}

