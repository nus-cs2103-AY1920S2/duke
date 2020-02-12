package javafx;

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
import main.Launcher;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Creates a new dialog box for the user side with the specified text and image.
     *
     * @param text the text of the dialog box.
     * @param img  the image associated with the user.
     * @return a new user dialog box with the specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {

        return new DialogBox(text, img);
    }

    /**
     * Creates a new dialog box for main.Duke's side with the specified text and image.
     *
     * @param text the text of the dialog box.
     * @param img  the image associated with main.Duke.
     * @return a new main.Duke dialog box with the specified text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {

        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

}