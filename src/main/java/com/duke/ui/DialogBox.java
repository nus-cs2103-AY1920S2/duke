package com.duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.setText(text);
            dialog.setWrappingWidth(280);
            dialog.setTextAlignment(TextAlignment.RIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        this.dialog.setTextAlignment(TextAlignment.LEFT);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets a user dialog box that displays the text and image given.
     * @param text The text to be displayed on the dialog box.
     * @param img The image to be displayed on the dialog box.
     * @return A user dialog box that contains the text and image to be displayed.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets a duke dialog box that displays the text and image given.
     * @param text The text to be displayed on the dialog box.
     * @param img The image to be displayed on the dialog box.
     * @return A duke dialog box that contains the text and image to be displayed.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}