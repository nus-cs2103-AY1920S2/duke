package seedu.java.util;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public abstract class DialogBox extends HBox {
    @FXML
    protected Label dialog;
    @FXML
    protected ImageView displayPicture;
}
