package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */

public class MainWindow extends AnchorPane {

    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showGreeting();
    }

    /**
     * Links the Controller to the <code>Duke</code> object of the program.
     *
     * @param d <code>Duke</code> object constructed when the program starts.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    private void setupScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setupDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void setupUserInput() {
        userInput.setPrefWidth(325.0);
        userInput.setOnAction((event) -> handleUserInput());
    }

    private void setupSendButton() {
        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> handleUserInput());
    }

    /**
     * Creates a greeting dialog box
     */
    private void showGreeting() {
        Label message = new Label(Ui.greeting());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, new ImageView(dukeImage)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label input = new Label(userInput.getText());
        Label response = new Label(duke.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, new ImageView(userImage)),
                DialogBox.getDukeDialog(response, new ImageView(dukeImage))
        );
        if (duke.isTerminated) {
            Platform.exit();
        }
        userInput.clear();
    }
}
