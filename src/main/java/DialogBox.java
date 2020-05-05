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
import javafx.scene.layout.Region;

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
        //dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
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

    public static DialogBox getUserDialog(String text, Image img) {
        var userDialog = new DialogBox(text, img);
        userDialog.dialog.setStyle("-fx-background-color: #D0F0C0; -fx-padding: 6 11 6 11; -fx-background-radius: 14;");
        userDialog.dialog.setTranslateY(20);
        userDialog.dialog.setTranslateX(-5);
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var dukeDialog = new DialogBox(text, img);
        dukeDialog.dialog.setStyle("-fx-background-color: #ADD8E6; -fx-padding: 6 11 6 11; -fx-background-radius: 14;");
        dukeDialog.flip();
        return dukeDialog;
    }
}