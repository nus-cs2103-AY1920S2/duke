package duke;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The <code>DialogBox</code> class represents a dialog box consisting of an ImageView to
 * represent the speaker as well as a Label containing text from the speaker.
 *
 * <p>Adapted from JavaFx Tutorial initially written by Jeffry Lum.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        dialog.setText(text);
        dialog.setFont(Font.font("UD Digi Kyokasho NP-R", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        dialog.setWrapText(true);
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        displayPicture.setImage(img);
        Circle clip = new Circle(50, 50, 46);
        displayPicture.setClip(clip);
        BackgroundFill fill = new BackgroundFill(Color.rgb(0xdc, 0xf8, 0xc6),
                new CornerRadii(7),
                Insets.EMPTY);
        Background background = new Background(fill);
        dialog.setBackground(background);
        dialog.setPadding(new Insets(2, 5, 2, 5));
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
     * Gets the user dialog box.
     *
     * @param text Dialog text.
     * @param img User image.
     * @return The dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    
    /**
     * Gets the Duke dialog box.
     *
     * @param text Dialog text.
     * @param img Duke image.
     * @return The dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
