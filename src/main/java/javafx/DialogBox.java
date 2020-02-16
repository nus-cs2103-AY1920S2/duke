package javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.Launcher;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        Circle clip = new Circle(40, 40, 35);
        displayPicture.setClip(clip);
    }

    /**
     * Creates a new dialog box for the user side with the specified text and image.
     *
     * @param text the text of the dialog box.
     * @param img  the image associated with the user.
     * @return a new user dialog box with the specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {

        DialogBox userDialogBox = new DialogBox(text, img);
        userDialogBox.setBackground(new Background(new BackgroundFill(Color.rgb(204, 229, 255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        return userDialogBox;
    }

    /**
     * Creates a new dialog box for Aelita's side with the specified text and image.
     *
     * @param text the text of the dialog box.
     * @param img  the image associated with Aelita.
     * @return a new Aelita dialog box with the specified text and image.
     */
    public static DialogBox getAelitaDialog(String text, Image img) {

        DialogBox aelitaDialogBox = new DialogBox(text, img);
        aelitaDialogBox.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        aelitaDialogBox.flip();
        return aelitaDialogBox;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

}