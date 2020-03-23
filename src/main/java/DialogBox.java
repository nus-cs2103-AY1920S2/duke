import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.shape.Rectangle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label name;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String name, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name.setText(name.toUpperCase() + ": ");
        dialog.setText(text);
        displayPicture.setImage(img);
        // got from online
        //@@author wxwxwxwx9-reused
        // Reused from https://stackoverflow.com/questions/25622445/how-to-make-imageviews-rounded with minor modifications
        Rectangle clip = new Rectangle(
            displayPicture.getFitWidth(), displayPicture.getFitHeight()
        );
        clip.setArcWidth(90);
        clip.setArcHeight(90);
        displayPicture.setClip(clip);
    }

    public static DialogBox getUserDialog(String name, String text, Image img) {
        DialogBox db = new DialogBox(name, text, img);
        return db;
    }

    public static DialogBox getDukeDialog(String name, String text, Image img) {
        DialogBox db = new DialogBox(name, text, img);
        db.setStyle("-fx-background-color: #e2dfdb");
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

}