import java.io.IOException;
import java.util.Collections;

import javafx.application.Application;
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
import javafx.scene.text.Font;
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

    // Format constant.
    private static final Color userBackground = Color.rgb(39, 130, 215);
    private static final Color dukeBackground = Color.rgb(216, 78, 67);

    private DialogBox(String text, Image img) {
        super(20); // Add spaces.
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTextFill(Color.rgb(255, 255, 255));
        dialog.setFont(new Font(12));
        dialog.setBackground(new Background(new BackgroundFill(userBackground,
                CornerRadii.EMPTY,
                new Insets(-6, -6, -10, -6))));
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        dialog.setBackground(new Background(new BackgroundFill(dukeBackground,
                CornerRadii.EMPTY,
                new Insets(-6, -6, -10, -6))));
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Generate a dialog box based on User's input.
     *
     * @param text The contents of User's input.
     * @param img User's image.
     * @return A new DialogBox contents the text and correspond image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Generate a dialog box based on Duke's reply.
     *
     * @param text The contents of Duke's reply.
     * @param img Duke's image.
     * @return A new DialogBox contents the text and correspond image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}