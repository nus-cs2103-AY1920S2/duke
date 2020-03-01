package com.duke.bot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the dealog box that contains the a message and the profile picture of the sender in JavaFX.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a DialogBox object.
     *
     * @param l The label containing the message being sent.
     * @param iv The image of the user.
     */
    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitHeight(100);
        displayPicture.setFitWidth(100);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a DialogBox for the user.
     *
     * @param l Message from the user.
     * @param iv Image of the user.
     * @return A DialogBox object.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Factory method to create a DialogBox for Duke Bot.
     *
     * @param l Message from Duke Bot.
     * @param iv Image of Duke Bot.
     * @return A DialogBox object.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
