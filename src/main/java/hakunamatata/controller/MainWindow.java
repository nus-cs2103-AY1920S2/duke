package hakunamatata.controller;

import com.jfoenix.controls.JFXScrollPane;

import hakunamatata.HakunaMatata;
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

    private HakunaMatata hakunaMatata;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/fe_profile.png"));
    private Image hakunaMatataImage = new Image(this.getClass().getResourceAsStream("/images/op1mdpi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        JFXScrollPane.smoothScrolling(scrollPane);
    }

    /**
     * Sets <code>HakunaMatata</code> for program.
     */
    public void setHakunaMatata(HakunaMatata d) {
        hakunaMatata = d;
        dialogContainer.getChildren().add(
                DialogBox.getHakunaMatataDialog(hakunaMatata.getUi().getWelcome(), hakunaMatataImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing HakunaMatata's reply
     * and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hakunaMatata.run(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHakunaMatataDialog(response, hakunaMatataImage)
        );
        userInput.clear();
    }
}