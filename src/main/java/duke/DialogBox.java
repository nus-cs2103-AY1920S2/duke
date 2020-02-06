package duke;

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
import javafx.scene.shape.Circle;

/**
 * A custom control using FXML.
 * This control represents a dialog box consisting of an Image to represent the speaker's face and a label
 * containing text from the speaker. The same goes for Duke.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param text the String to include as text.
     * @param img the profile photo for user identification.
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

        // Set Label and ImageView.
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 45));

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
     * Produces a DialogBox to represent the user's command.
     *
     * @param text the String to include as text.
     * @param img the profile photo for user identification.
     * @return DialogBox representing user command.
     */
    public static DialogBox getUserDialog(String text, Image img) {

        DialogBox db = new DialogBox(text, img);

        // Special Effect: For user, command chats are blue in colour.
        db.setStyle("-fx-background-color:POWDERBLUE");

        return db;

    }

    /**
     * Produces a DialogBox to represent Duke's response.
     *
     * @param text the String to include as text.
     * @param img the profile photo for Duke.
     * @return DialogBox representing Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {

        DialogBox db = new DialogBox(text, img);

        // Special Effect: For Duke, response chats are on the left of the chatbox.
        db.flip();

        return db;
    }
}