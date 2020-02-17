package duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the duke and a label containing text
 * from the speaker.
 */
class DukeDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle avatar;

    /**
     * Initialise the dialog box of duke with the specified text to be displayed.
     *
     * @param text The text to be displayed.
     */
    DukeDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DukeDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        avatar.setFill(
                new ImagePattern(new Image(getClass().getResourceAsStream("/images/DukeAvatar.png"))));
    }
}