package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * DialogBox class for making Duke chat/command & response presentable.
 */
public class DialogBox extends HBox {

    /**
     * Text for the chat box.
     */
    private Label text;

    /**
     * Display picture for identification in the chat box.
     */
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param l the label to represent as text.
     * @param iv the profile photo for identification.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

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

    /**
     * Produces a DialogBox to represent the user's command.
     *
     * @param l the label to include as text.
     * @param iv the profile photo for user identification.
     * @return DialogBox representing user command.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {

        DialogBox db = new DialogBox(l, iv);

        // Special Effect: For user, command chats are blue in colour.
        db.setStyle("-fx-background-color:POWDERBLUE");

        return db;
    }

    /**
     * Produces a DialogBox to represent Duke's response.
     *
     * @param l the label to include as text.
     * @param iv the profile photo for Duke.
     * @return DialogBox representing Duke's response.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {

        DialogBox db = new DialogBox(l, iv);

        // Special Effect: For Duke, response chats are on the left of the chatbox.
        db.flip();

        return db;
    }

}