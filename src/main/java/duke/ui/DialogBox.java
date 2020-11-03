package duke.ui;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.layout.Region;

import duke.exceptions.DukeException;

/**
 * This control represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    // @@author joel-lim-reused
    // Reused from JavaFx Tutorial by Jeffry Lum.
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) throws DukeException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new DukeException("Unable to load fxml!");
        }
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets a DialogBox with the User's image.
     */
    public static DialogBox getUserDialog(String text, Image img) throws DukeException {
        return new DialogBox(text, img);
    }

    /**
     * Gets a flipped DialogBox with Duke's image.
     */
    public static DialogBox getDukeDialog(String text, Image img) throws DukeException {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    // @@author
}