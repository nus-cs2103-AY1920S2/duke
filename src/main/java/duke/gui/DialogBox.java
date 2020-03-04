package duke.gui;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent 
 * the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box given a string, image and color.
     * @param text The text of the dialog.
     * @param image The avatar image.
     * @param color The background color of the dialog.
     */
    private DialogBox(String text, Image image, Color color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setBackground(new Background(
                new BackgroundFill(color, new CornerRadii(5), Insets.EMPTY)));
        displayPicture.setImage(image);

        Circle circle = new Circle(40, 40, 40);
        displayPicture.setClip(circle);

        dialog.setWrapText(true);
        displayPicture.setFitWidth(80.0);
        displayPicture.setFitHeight(80.0);
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

    /**
     * Gets user dialog box.
     * @param text The user input.
     * @param img The user avatar.
     * @return The dialog box with user avatar and input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, Color.LIGHTGRAY);
    }

    /**
     * Gets duke dialog box.
     * @param text Duke's response.
     * @param img Duke's avatar.
     * @return The dialog box with Duke's avatar and response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, Color.PALETURQUOISE);
        db.flip();
        return db;
    }
}