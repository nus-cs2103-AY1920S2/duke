package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object.
     *
     * @param text String text for the DialogBox
     * @param img Image image for the DialogBox
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

        setDialogBox(text, img);
    }


    /**
     * Sets the DialogBox and clipping the image into a round icon.
     *
     * @param text String text for the DialogBox
     * @param img Image image for the DialogBox
     */
    public void setDialogBox(String text, Image img) {
        dialog.setPadding(new Insets(10));
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(95, 90, 95);
        displayPicture.setClip(clip);
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }


    /**
     * Customises the User DialogBox.
     *
     * @param text String text for the DialogBox
     * @param img Image image for the DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_RIGHT);
        db.setStyle("-fx-font: 38 Papyrus; -fx-font-weight: bold");
        return db;
    }


    /**
     * Customises the Duke DialogBox.
     *
     * @param text String text for the DialogBox
     * @param img Image image for the DialogBox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-font: 13 Consolas; -fx-font-weight: bold");

        return db;
    }
}
