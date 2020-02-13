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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a dialog
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

        double w = displayPicture.getFitWidth();
        double h = displayPicture.getFitHeight();
        Ellipse ellipse = new Ellipse(w / 2.7, h / 2.7, w / 2.7, h / 2.7);
        displayPicture.setClip(ellipse);

        dialog.setMinHeight(Label.USE_PREF_SIZE);
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setSpacing(5);
        db.setStyle("-fx-padding: 5;");
        db.dialog.setStyle("-fx-background-color: linear-gradient(#f7d2d4, #deadaf, #f7d2d4);"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;"
                + "-fx-text-fill: #85464a;"
                + "-fx-stroke-width: 50;"
                + "-fx-stroke: pin;");
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setSpacing(5);
        db.setStyle("-fx-padding: 5;");
        db.dialog.setStyle("-fx-background-color: #ffffff;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;"
                + "-fx-text-fill: #9b6a6c;"
                + "-fx-max-height: 400;");
        return db;
    }
}