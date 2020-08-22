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

//@@author JeffreyLum-reused
//reused from https://github.com/zsoh97/duke/blob/master/tutorials/javaFxTutorialPart4.md with minor modifications.
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
     * Constructor for DialogBox.
     * @param text Text to be displayed in DialogBox.
     * @param img Image to be displayed in DialogBox.
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

        dialog.setText(text);
        displayPicture.setImage(img);
        Circle circleClip = new Circle(50, 50, 48);
        displayPicture.setClip(circleClip);
        this.setBackground(new Background(new BackgroundFill(Color.WHEAT,
                new CornerRadii(3), new Insets(3))));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        this.setBackground(new Background(new BackgroundFill(Color.SLATEGRAY,
                new CornerRadii(3), new Insets(3))));
    }

    /**
     * Returns new DialogBox with the text and image to be displayed.
     * @param text Text to be displayed in DialogBox.
     * @param img Image to be displayed in DialogBox.
     * @return DialogBox containing the user's image and the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns new DialogBox with the text and image to be displayed.
     * @param text Text to be displayed in DialogBox.
     * @param img Image to be displayed in DialogBox.
     * @return DialogBox containing Duke's image and the Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}