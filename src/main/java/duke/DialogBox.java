package duke;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int DIALOG_POSITION = 0;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setAlignment(Pos.BOTTOM_RIGHT);
        displayPicture.setImage(image);
        setAlignment(Pos.BOTTOM_RIGHT);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns the dialog box containing Duke's messages.
     *
     * @param text the messages from Duke to be displayed
     * @param image the image of Duke
     * @return the dialog box containing Duke's messages
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        DialogBox db = new DialogBox(text, image);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        dialog.setAlignment(Pos.BOTTOM_LEFT);
        tmp.set(DIALOG_POSITION, dialog);
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.BOTTOM_LEFT);
    }
}
