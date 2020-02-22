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
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a dialog
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox that takes in a string text message as well as the image of the the sender.
     *
     * @param text the text that is to be sent by the user.
     * @param img the Image to be used in the Dialog box.
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

        // Solution below adapted from https://stackoverflow.com/a/38010904
        double w = displayPicture.getFitWidth();
        double h = displayPicture.getFitHeight();
        Ellipse ellipse = new Ellipse(w / 2.7, h / 2.7, w / 2.7, h / 2.7);
        displayPicture.setClip(ellipse);

        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);
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

    /**
     * Creates a dialog box by the user, with certain modifications to the text colour and dialog box colour.
     * @param text the message the user inputs
     * @param img the image represented by the user
     * @return the Dialogbox of the user
     */

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setSpacing(5);
        db.setStyle("-fx-padding: 5;");
        //@@author {ybchen97}-reused setStyle code template with some modifications
        db.dialog.setStyle("-fx-background-color: linear-gradient(#f7d2d4, #deadaf, #f7d2d4);"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;"
                + "-fx-text-fill: #85464a;"
                + "-fx-stroke-width: 50;"
                + "-fx-stroke: pin;");
        //@@author
        return db;
    }

    /**
     * Creates a dialog box by the bot, with certain modifications to the text colour and dialog box colour.
     * @param text the message the bot replies with
     * @param img the image represented by the bot
     * @return the Dialogbox of the bot
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setSpacing(5);
        db.setStyle("-fx-padding: 5;");
        //@@author {ybchen97}-reused setStyle code template with some modifications
        db.dialog.setStyle("-fx-background-color: #ffffff;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;"
                + "-fx-text-fill: #9b6a6c;"
                + "-fx-max-height: 400;");
        //@@author
        return db;
    }
}