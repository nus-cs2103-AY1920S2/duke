package duke.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the dialog box component of the GUI of the program.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(Label text, ImageView image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text.getText());
        displayPicture.setImage(image.getImage());
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
     * Returns a <code>DialogBox</code> object representing the user.
     * The dialog box will echo the user's input.
     *
     * @param text text input by the user.
     * @param image profile picture of the user.
     * @return a <code>DialogBox</code> object representing the user, with the specified text and profile image.
     */
    public static DialogBox getUserDialog(Label text, ImageView image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns a <code>DialogBox</code> object representing Duke.
     * The dialog box will contain Duke's reply.
     *
     * @param text text output by Duke.
     * @param image profile picture of Duke.
     * @return a <code>DialogBox</code> object representing Duke, with the specified text and profile image.
     */
    public static DialogBox getDukeDialog(Label text, ImageView image) {
        var db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}
