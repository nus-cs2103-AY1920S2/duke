package duke;

import javafx.geometry.Pos;

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
}