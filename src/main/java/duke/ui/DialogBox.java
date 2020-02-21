package duke.ui;

// import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Code taken from JavaFX tutorial.
 * Custom control DialogBox to represent the dialog boxes composed of an ImageView to represent the speaker's face 
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        // try {
        //     FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        //     fxmlLoader.setController(this);
        //     fxmlLoader.setRoot(this);
        //     fxmlLoader.load();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        displayPicture = new ImageView(img);
        dialog = new Label(text);

        displayPicture.setFitWidth(100);
        displayPicture.setFitHeight(100);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(dialog, displayPicture);
        displayPicture.setClip(new Circle(50, 50, 45)); // Clip the ImageView into a circle

        dialog.setText(text);
        displayPicture.setImage(img);

        displayPicture.setClip(new Circle(50, 50, 45)); // Clip the ImageView into a circle
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}