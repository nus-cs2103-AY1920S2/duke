package ip.view;

import ip.Duke;
import ip.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button sendButton;

    private Duke duke;

    private Image leftImage = new Image(this.getClass().getResourceAsStream("/images/left_pig.png"));
    private Image rightImage = new Image(this.getClass().getResourceAsStream("/images/right_red.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null;
        assert dialogContainer != null;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        dialogContainer.getChildren().add(DialogBox.getRightDialog(Ui.initialGreeting(), rightImage));
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
        assert userInput != null;
        assert dialogContainer != null;
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getLeftDialog(input, leftImage),
                DialogBox.getRightDialog(response, rightImage)
        );
        userInput.clear();
        if (response.equals("")){
            return;
        }
        if (response.equals(Ui.GOODBYE)){
            duke.exit();
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {}
                Platform.exit();
            }).start();
        }
    }
}
