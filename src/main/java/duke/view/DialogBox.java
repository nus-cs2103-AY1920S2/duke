package duke.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Code taken from JavaFX tutorial.
 * Custom control DialogBox to represent the dialog boxes composed of two different ImageView and Label controls.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    static final double fitWidth = 100;
    static final double FitHeight = 100;

    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(fitWidth);
        displayPicture.setFitHeight(FitHeight);

        displayPicture.setClip(new Circle(50, 50, 45)); // Clip the ImageView into a circle

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(5); // Add padding between each ImageView and its Label
        this.setBackground(new Background(new BackgroundFill(
            Color.gray(0.865), CornerRadii.EMPTY, Insets.EMPTY))); // Add background color to each dialog box
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
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}