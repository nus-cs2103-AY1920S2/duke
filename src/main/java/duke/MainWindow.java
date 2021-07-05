package duke;

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

    private Duke duke = new Duke();
    private Ui ui = new Ui();
    private Storage storage = new Storage();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nobita.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/doraemon.png"));

    @FXML
    public void initialize() throws IOException {
        storage.startReading();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String start = ui.opening();
        dialogContainer.getChildren().addAll(

                DialogBox.getDukeDialog(start, dukeImage)
        );

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
        Parser parser = new Parser();
        String input = userInput.getText();
        String response;
        if (input.equals("bye")) {
            response = ui.ending();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();

        } else {
            response = parser.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );

        }
        userInput.clear();


    }
}