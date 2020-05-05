package duke;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

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

    /**
     * Constructs dialog box according to set up in DialogBox.fxml.
     * @param text The text of the dialog box.
     * @param img The image of the dialog box.
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
        setDialogBox(text, img);
    }

    /**
     * Sets the dialog box with the image and text.
     */
    public void setDialogBox(String text, Image img) {
        dialog.setPadding(new Insets(10));
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(49.5, 49.5, 49.5);
        displayPicture.setClip(clip);
        this.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        dialog.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));
    }

    /**
     * Outputs the dialog box that are produced by the user's input.
     * @param text The text input from the user.
     * @param img The user's image display.
     * @return DialogBox object of the user's dialog with the text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Outputs the dialog box that are produced by the chat bot.
     * @param text The text input from the user.
     * @param img The user's image display.
     * @return DialogBox object of the user's dialog with the text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }
}
