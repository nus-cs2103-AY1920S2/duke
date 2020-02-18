package dukeproj.gui;

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

import java.io.IOException;
import java.util.Collections;

/**
 * Represents the dialog box in the dialog container of Duke's GUI.
 */
public class DialogBox extends HBox {
    /**
     * Creates a user dialog box representing the user.
     *
     * @param label The String inputted by the user.
     * @param image The image that represents the user.
     * @return The user's dialog box.
     */
    public static DialogBox getUserDialog(String label, Image image) {
        return new DialogBox(label, image);
    }

    /**
     * Creates a Duke dialog box representing Duke.
     *
     * @param label The String that contains the response by Duke.
     * @param image The image that represents Duke.
     * @return Duke's dialog box.
     */
    public static DialogBox getDukeDialog(String label, Image image) {
        DialogBox db = new DialogBox(label, image);
        db.flip();
        return db;
    }

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
}
