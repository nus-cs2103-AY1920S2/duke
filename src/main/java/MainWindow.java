import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Duke duke;
    private final static String INTERRUPT_MESSAGE = "Please do not stop me from sleeping ><  :(   \n";


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PikachuCircle2.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/KirbyCircle2.png"));
    private Image bengImage = new Image(this.getClass().getResourceAsStream("/images/AhBeng2.png"));
    private Image pckImage = new Image(this.getClass().getResourceAsStream("/images/PCK2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        String greeting = duke.getResponse("Greeting");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if(input.toLowerCase().equals("nbcb")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, bengImage)
            );
        } else if (input.toLowerCase().equals("wuhan") || input.toLowerCase().equals("coronavirus")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, pckImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();

        if(!duke.isActive()) {
            try {
                TimeUnit.SECONDS.sleep(1);
                exit();
            } catch (InterruptedException e) {
                dialogContainer.getChildren().add(
                        DialogBox.getDukeDialog(INTERRUPT_MESSAGE, userImage)
                );
            }
        }
    }

    /**
     * Exits with status 0.
     */
    private void exit() {
        System.exit(0);
    }


}