package views;

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
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class ChatBox extends HBox {
    @FXML
    private Label dialog;
    
    @FXML
    private ImageView displayPicture;

    private ChatBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ChatBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void setDialog(String text) {
        this.dialog.setText(text);
    }
    
    private void setImage(Image img) {
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private static void flip(ChatBox node) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(node.getChildren());
        Collections.reverse(tmp);
        node.getChildren().setAll(tmp);
        node.setAlignment(Pos.TOP_LEFT);
    }

    public static ChatBox getUserDialog(String text, Image img) {
        try {
            ChatBox node = ChatBox.getDialog(text, img);
            return node;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ChatBox getDukeDialog(String text, Image img) {
        try {
            ChatBox node = ChatBox.getDialog(text, img);
            ChatBox.flip(node);
            return node;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ChatBox getDialog(String text, Image img) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/ChatBox.fxml"));
        ChatBox node = fxmlLoader.<ChatBox>load();
        fxmlLoader.<ChatBox>getController().setDialog(text);
        fxmlLoader.<ChatBox>getController().setImage(img);
        return node;
    }
}