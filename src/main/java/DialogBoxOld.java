import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DialogBoxOld extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBoxOld(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        displayPicture.setStyle("-fx-padding: 10");

        this.setAlignment(Pos.TOP_RIGHT);
        this.setStyle("-fx-padding: 10");
        this.setStyle("-fx-background-color: lightgrey");

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

    public static DialogBoxOld getUserDialog(Label l, ImageView iv) {
        return new DialogBoxOld(l, iv);
    }

    public static DialogBoxOld getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBoxOld(l, iv);
        db.flip();
        return db;
    }

}

