import javafx.application.Platform;
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

    private Duke duke;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeRespond(ui.welcomeMessage()), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public String dukeRespond(String message) {
        return "_______________________________________________________" +
                "\n" + message + "\n" +
                "_______________________________________________________";
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String dukeResponse = "";
        String input = userInput.getText();

        if (input.equals("bye")) {
            // Solution below adapted from https://stackoverflow.com/questions/52393982/
            // javafx-problem-with-platform-runlater-delayed-rendering-of-canvas-graphic
                new Thread(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException exc) {
                        dialogContainer.getChildren().addAll(
                                DialogBox.getUserDialog(input, userImage),
                                DialogBox.getDukeDialog(dukeRespond(exc.getMessage()), dukeImage)
                        );
                    }
                    Platform.exit();
                }).start();
        } else {
            String response = new Parser(input, ui).detectError(duke.getTaskList());

            if (response.equals("")) {
                dukeResponse = dukeRespond(duke.executeCommmand(input, duke.getTaskList(), ui, duke.getStorage()));
            } else {
                dukeResponse = dukeRespond(response);
            }

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(dukeResponse, dukeImage)
            );
        }
        userInput.clear();
    }
}