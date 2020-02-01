package duke.gui;

import javafx.geometry.Pos;
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
    }
}
