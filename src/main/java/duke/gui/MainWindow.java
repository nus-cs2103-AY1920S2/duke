package duke.gui;

import duke.Duke;
import duke.Ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for duke.gui.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        loadStartUp(d.getUi());
    }

    /**
     * Displays the start-up message user suppose to see upon starting of
     * EXE. Welcome message should never be empty.
     *
     * @param ui Ui object which handles all message that is to be displayed to user.
     */
    public void loadStartUp(Ui ui) {
        String welcome = ui.showGuiWelcome();
        assert !welcome.isEmpty();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcome, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert duke != null;
        assert !userImage.isBackgroundLoading();
        assert !dukeImage.isBackgroundLoading();
        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert !response.isEmpty();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals(duke.getUi().showGoodByeMessage())) {
            Platform.exit();
        }
    }


}