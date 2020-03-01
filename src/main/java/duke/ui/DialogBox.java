package duke.ui;

import java.io.IOException;

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
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    /** The text attached to this dialog box. */
    @FXML
    private Label dialog;
    /** The image attached to this dialog box. */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new dialog box with the provided text and image.
     *
     * @param text the GUI text for the dialog box.
     * @param img the GUI image for the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        displayPicture.setImage(img);

        // Link?
        double radius = displayPicture.getFitWidth() / 2;
        displayPicture.setClip(new Circle(radius, radius, radius));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> node = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(node);
        this.getChildren().setAll(node);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Constructs a dialog box for the user.
     *
     * @param text the GUI text for the dialog box.
     * @param img the GUI image for the user.
     * @return a dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Constructs a dialog box for Duke.
     *
     * @param text the GUI text for the dialog box.
     * @param img the GUI image for Duke.
     * @return a dialog box for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
