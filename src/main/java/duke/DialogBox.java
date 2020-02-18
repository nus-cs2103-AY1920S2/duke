package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


/**
 * Dialog boxes are composed of 2 different controls in the mockup of the Ui for Duke - ImageView and Label, and are reused multiple times.
 * This is a custom control.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private Circle circle = new Circle(40, Color.WHITE);

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setFont(new Font("Rockwell", 12));
        text.setPadding(new Insets(15,12,0,12));
        displayPicture.setFitWidth(70.0);
        displayPicture.setFitHeight(70.0);

        StackPane imageContainer = new StackPane();
        imageContainer.getChildren().addAll(circle, displayPicture);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, imageContainer);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}