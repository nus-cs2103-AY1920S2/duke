package bot.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogueBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a custom HBox, for use
     * in 4LC3N-BOT's GUI
     *
     * @param l The label containing dialogue text
     * @param iv Image of an user who generated
     *           the dialogue
     */
    public DialogueBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        // displayPicture.setFitWidth(100.0);
        // displayPicture.setFitHeight(100.0);
        displayPicture.setClip(
                new Circle(50.0, 50.0, 50.0)
        );

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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
     * @param l The label containing dialogue text
     * @param iv Image of the user
     * @return A new DialogBox, formatted to the right
     */
    public static DialogueBox getUserBox(Label l, ImageView iv) {
        return new DialogueBox(l, iv);
    }

    /**
     * Formats this DialogueBox to the left. To be used
     * for bot output
     *
     * @param l The label containing dialogue text
     * @param iv Image of the bot
     * @return A new DialogBox, formatted to the left
     */
    public static DialogueBox getBotBox(Label l, ImageView iv) {
        DialogueBox db = new DialogueBox(l, iv);
        db.flip();
        return db;
    }
}