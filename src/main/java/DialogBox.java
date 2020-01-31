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

import java.io.IOException;

/*
 * DialogBox
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 31 Jan 2020
 *
 */

/**
 * DialogBox class represents the line in which
 * the message is being displayed.
 * @author Mario Lorenzo
 */

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox instance.
     * @param text The message that is being displayed.
     * @param img The image of the sender.
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
    }

    /**
     * Flips the position between such that the user and Duke
     * can be differentiated.
     */

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box when Duke wants to send a message.
     * @param text The message that Duke wants to show.
     * @param img The Duke image.
     * @return The DialogBox instance.
     */

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box when the user wants to send a command.
     * @param text The command message that the user wants to send to Duke.
     * @param img The image of the user.
     * @return The DialogBox instance.
     */

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}