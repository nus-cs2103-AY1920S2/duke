package duke.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.util.Collections;

/**
 * The {@code DialogBox} control represents a dialog box consisting of an ImageView to
 * represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String USER_SPEECH_BUBBLE_CLASS = "user-speech-bubble";

    @FXML
    private HBox SpeechBubble;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);
        double width = displayPicture.getFitWidth();
        double height = displayPicture.getFitHeight();
        Ellipse ellipse = new Ellipse(width / 2, height / 2, width / 2, height / 2);
        displayPicture.setClip(ellipse);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Returns user dialog.
     *
     * @param text Text in the dialog.
     * @param img  Image for dialog.
     * @return Dialog for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.SpeechBubble.getStyleClass().add(USER_SPEECH_BUBBLE_CLASS);
        return userDialog;
    }

    /**
     * Returns Duke dialog.
     *
     * @param text Text in the dialog.
     * @param img  Image of dialog.
     * @return Dialog for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
