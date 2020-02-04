package bot.gui;

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

public class DialogueBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for a custom HBox, for use
     * in 4LC3N-BOT's GUI
     *
     * @param text The label containing dialogue text
     * @param img Image of an user who generated
     *           the dialogue
     */
    public DialogueBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        this.displayPicture.setImage(img);
    }

    /**
     * Moves this DialogueBox to the left instead
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Formats this DialogueBox to the right. To be used
     * for user input
     *
     * @param s The string containing dialogue text
     * @param im Image of the user
     * @return A new DialogBox, formatted to the right
     */
    public static DialogueBox getUserBox(String s, Image im) {
        return new DialogueBox(s, im);
    }

    /**
     * Formats this DialogueBox to the left. To be used
     * for bot output
     *
     * @param s The String containing dialogue text
     * @param im Image of the bot
     * @return A new DialogBox, formatted to the left
     */
    public static DialogueBox getBotBox(String s, Image im) {
        DialogueBox db = new DialogueBox(s, im);
        db.flip();
        return db;
    }
}