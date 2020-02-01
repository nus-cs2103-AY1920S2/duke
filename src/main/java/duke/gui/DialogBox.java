package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/** Represents the dialog box of GUI. */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Returns a new DialogBox instance.
     *
     * @param label text response
     * @param imageView display picture
     */
    public DialogBox(Label label, ImageView imageView) {
        this.text = label;
        this.displayPicture = imageView;
        // Format label
        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);
        // Set alignment of instance
        this.setAlignment(Pos.TOP_RIGHT);
        // Add label and image
        this.getChildren().addAll(text, displayPicture);
        // Set spacing
        this.setSpacing(10.0);
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
     * Returns a DialogBox instance representing User's input.
     *
     * @param label contains text to be displayed
     * @param imageView contains image to be displayed
     * @return DialogBox instance representing user input
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Returns a flipped DialogBox instance representing Duke's response.
     *
     * @param label contains text to be displayed
     * @param imageView contains image to be displayed
     * @return flipped DialogBox instance
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        DialogBox dialogBox = new DialogBox(label, imageView);
        dialogBox.flip();
        return dialogBox;
    }
}
