import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke = new Duke("Data/Duke.txt");

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    private String getResponse(String input) { // temp change to public
        return " 연인 <3: \n" + input;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input.length() > 0 : "The user's input is not valid.";
        Parser parser = new Parser(input.split(" "));
        String command = parser.getCommand();
        String response = this.getResponse(this.duke.run(input));

       this.dialogContainer.setStyle("-fx-border-color:GREEN;-fx-background-color:#ffb508");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        this.isGoodbye(input);
    }
    /**
     * Check the user input to verify if duke should bid goodbye to user.
     */

    private void isGoodbye(String userInput) {
        if (userInput.equals("bye")) {
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.close();
        }
    }
}