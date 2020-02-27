package duke;

//@@author j-lum-reused
//Reused from JavaFX Tutorial Part 4 with modifications
import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

/**
 * A controller for <code>DialogBox</code>, which consists of an <code>ImageView</code> of the person's display picture
 * and a <code>Label</code> containing the person's text.
 */
public class DialogBox extends HBox {
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label dialog;

    private DialogBox(Image image, String text, Color color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayPicture.setImage(image);
        dialog.setText(text);
        dialog.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10), null)));
        setAlignment(Pos.BOTTOM_LEFT);
    }

    /**
     * Returns the dialog box containing the received messages.
     *
     * @param text the messages received by the user
     * @param image the display picture of the other party
     * @return the dialog box containing the received messages
     */
    public static DialogBox getReceivedDialog(Image image, String text, Color color) {
        return new DialogBox(image, text, color);
    }

    /**
     * Returns the dialog box containing the sent messages.
     *
     * @param text the messages sent by the user
     * @param image the display picture of the user
     * @return the dialog box containing the sent messages
     */
    public static DialogBox getSentDialog(Image image, String text, Color color) {
        DialogBox dialogBox = new DialogBox(image, text, color);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Flips the dialog box such that the display picture is on the right and text on the left.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.BOTTOM_RIGHT);
    }
}
//@@author
