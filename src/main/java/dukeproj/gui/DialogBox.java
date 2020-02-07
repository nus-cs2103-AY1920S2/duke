package dukeproj.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the dialog box in the dialog container of Duke's GUI.
 */
public class DialogBox extends HBox {
    /**
     * Creates a user dialog box representing the user.
     *
     * @param label The label that contains the String inputted by the user.
     * @param imageView The image that represents the user.
     * @return The user's dialog box.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Creates a Duke dialog box representing Duke.
     *
     * @param label The label that contains the response by Duke.
     * @param imageView The image that represents Duke.
     * @return Duke's dialog box.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }

    private Label text;
    private ImageView displayPicture;

    private DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;

        text.setWrapText(true);
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
