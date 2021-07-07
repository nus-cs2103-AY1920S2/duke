import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String LEFT_DIALOG_PATH = "/view/LeftDialogBox.fxml";
    private static final String RIGHT_DIALOG_PATH = "/view/RightDialogBox.fxml";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String sourcePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(sourcePath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Circle clip = new Circle(30, 30, 30);

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, RIGHT_DIALOG_PATH);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img, LEFT_DIALOG_PATH);
    }
}