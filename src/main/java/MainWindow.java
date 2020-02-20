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
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.dukeAnswer(ui.welcomeMessage()), dukeImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Storage storage = duke.getStorage();
        TaskList tasks = duke.getTaskList();
        String input = userInput.getText();

        if (input.equals("bye")) {
            // When user inputs "bye", exits the program.
            // Solution below adapted from https://stackoverflow.com/questions/52393982/
            // javafx-problem-with-platform-runlater-delayed-rendering-of-canvas-graphic
            new Thread(() -> {
                storage.updateFile(tasks, ui);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exc) {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(input, userImage),
                            DialogBox.getDukeDialog(duke.dukeAnswer(exc.getMessage()), dukeImage)
                    );
                }
                Platform.exit();
            }).start();

        } else {
            // Parse the command to the Parser class
            // method : parseCommand(). Gets the message
            // duke wants to tell the user.
            String dukeResponse = duke.dukeAnswer(new Parser(input, ui
                    ).parseCommand(tasks));

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(dukeResponse, dukeImage)
            );

        }
        userInput.clear();
    }
}