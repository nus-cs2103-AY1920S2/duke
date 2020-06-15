package views;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class ChatBox {
    @FXML
    private Label dialog;
    
    @FXML
    private ImageView displayPicture;

    private void setDialog(String text) {
        dialog.setText(text);
    }
    
    private void setImage(Image img) {
        displayPicture.setImage(img);
    }

    @FXML
    public void initialize() {
        Circle circleClip = new Circle(30, 30, 30);
        this.displayPicture.setClip(circleClip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * @param node The node to flip.
     * @return The flipped node.
     */
    private static HBox flip(HBox node) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(node.getChildren());
        Collections.reverse(tmp);
        node.getChildren().setAll(tmp);
        return node;
    }

    /**
     * Creates a user-sided ChatBox based on the input text and image.
     * @param text The text to show.
     * @param img The display image to show.
     * @return The node to be displayed.
     */
    public static HBox getUserDialog(String text, Image img) {
        try {
            HBox node = ChatBox.getDialog(text, img);
            return node;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a duke-sided ChatBox based on the input text and image.
     * @param text The text to show.
     * @param img The display image to show.
     * @return The node to be displayed.
     */
    public static HBox getDukeDialog(String text, Image img) {
        try {
            HBox node = ChatBox.getDialog(text, img);
            node = ChatBox.flip(node);
            return node;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HBox getDialog(String text, Image img) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatBox.class.getResource("/views/ChatBox.fxml"));
        HBox node = fxmlLoader.load();
        fxmlLoader.<ChatBox>getController().setDialog(text);
        fxmlLoader.<ChatBox>getController().setImage(img);
        return node;
    }
}