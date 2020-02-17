package duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This control represents a user dialog box consisting of a label containing text from the speaker.
 */
class UserDialogBox extends HBox {
    @FXML
    private Label dialog;

    /**
     * Initialise the dialog box of duke with the specified text and image to be displayed.
     *
     * @param text The text to be displayed.
     */
    UserDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }
}