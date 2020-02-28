package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv, int count) {


        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(120.0);
        displayPicture.setFitHeight(120.0);

        Image background1 = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        Image background2 = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        if(count == 1){

            // create a background fill
            BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,
                    new CornerRadii(5), Insets.EMPTY);

            // create Background
            Background background = new Background(background_fill);
            setBackground(background);
        }
        else{
            // create a background fill
            BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGREEN,
                    CornerRadii.EMPTY, Insets.EMPTY);

            // create Background
            Background background = new Background(background_fill);

            setBackground(background);
        }

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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, 1);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, 2);
        db.flip();
        return db;
    }
}