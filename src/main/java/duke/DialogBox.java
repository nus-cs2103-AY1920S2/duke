package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

/**
 * Creates a DialogBox for GUI to show the user's input and bot's response.
 */
public class DialogBox extends HBox {

    final Circle clip = new Circle(35, 50, 50);

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;



    /**
     * Creates a DialogBox for GUI to show the user's input and bot's response.
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
        displayPicture.setClip(clip);
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

    /**
     * Returns a DialogBox with user's input and image.
     * @param text User's Input
     * @param img Image of user
     * @return DialogBos with user's input and image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox with bot's response and image.
     * @param text Bot's response
     * @param img Image of bot
     * @return DialogBos with bot's reponse and image
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
