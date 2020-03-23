import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private Duke duke;

    protected final String userName = "Tom";
    protected final String dukeName = "Jerry";

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/TomEvil.gif"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/JerryDrunk.jpg"));

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
        if (!input.equals("")) {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userName, input, userImage),
                    DialogBox.getDukeDialog(dukeName, response, dukeImage)
            );
            userInput.clear();
            if (input.equals("bye")) {
                System.exit(0);
            }
        }
    }

    protected void printGuiIntro() {
        String intro = "hey bud waht cna i do for ku";
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(dukeName, intro, dukeImage)
        );
    }

}