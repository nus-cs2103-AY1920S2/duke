import javafx.animation.PauseTransition;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window of the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Background bg = new Background(new BackgroundFill(
                Color.web("#212121"), CornerRadii.EMPTY, new Insets(0, 0, 0, 0)));
        //inside offsets->0
        dialogContainer.setBackground(bg); //set background color to black
        greet();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void greet() {
        StringBuilder logo = new StringBuilder("Hello! I am DUKE.\n\n"
                + "These are the functions I provide:\n"
                + "1. Add a task- task may be of type todo, deadline or event\n"
                + "2. Delete a task\n"
                + "3. Find a task\n"
                + "4. Mark a task as completed/done\n"
                + "5. List all tasks\n"
                + "6. Exit the application\n\n"
                + "Please enter the respective number of a function for more information on usage!\n\n"
                + "NOTE: The application automatically deletes duplicated tasks");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(logo.toString(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        try {
            if (response.equalsIgnoreCase(EXIT_MESSAGE)) {
                Stage stage = Stage.class.cast(VBox.class.cast(dialogContainer).getScene().getWindow());
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
                pauseTransition.setOnFinished(event -> stage.close());
                pauseTransition.play();
            }
        } catch (Exception e) {
            System.exit(0);
        }
    }
}