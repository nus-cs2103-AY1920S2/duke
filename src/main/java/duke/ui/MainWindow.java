package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** ScrollPane object of the UI.*/
    @FXML
    private ScrollPane scrollPane;
    /** VBox object of the UI.*/
    @FXML
    private VBox dialogContainer;
    /** TextField object of the UI.*/
    @FXML
    private TextField userInput;
    /** Main program logic. */
    private Duke duke = new Duke();
    /** Image for the user. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** Image for the program. */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the main application window and prints welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcomeMessage(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * the program's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        //get duke's response based on user input.
        String input = userInput.getText();
        String response = duke.getResponse(input);

        //append dialog boxes to dialog container and clear user input.
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        //exit the program if required.
        if (input.equals("bye")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        stage.close();
                    }
                });
        }
    }
}
