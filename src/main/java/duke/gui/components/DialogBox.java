package duke.gui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object.
     *
     * @param l  a text label for the DialogBox.
     * @param iv image of the character contained in the DialogBox.
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

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox for the user.
     *
     * @param l  label for the user
     * @param iv image of the user
     * @return a DialogBox object
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a DialogBox for Duke.
     *
     * @param l  label for Duke
     * @param iv image of Duke
     * @return a DialogBox object
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
