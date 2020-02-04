package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    /** The text attached to this dialog box. */
    private Label text;
    /** The image attached to this dialog box. */
    private ImageView displayPicture;

    /**
     * Constructs a new dialog box with the provided text and image.
     *
     * @param l the GUI text for the dialog box.
     * @param iv the GUI image for the dialog box.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Constructs a dialog box for the user.
     *
     * @return a dialog box for the user.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Constructs a dialog box for Duke.
     *
     * @return a dialog box for Duke.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}