package duke.component;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a message component.
 */
public class MessageComponent extends HBox {
    public static enum Alignment {
        LEFT, RIGHT
    }

    @FXML
    private Circle profilePhoto;

    @FXML
    private Label message;

    private final String messageString;
    private final String profilePhotoUrl;
    private final Alignment alignment;

    /**
     * Constructs a message component.
     * 
     * @param messageString Message string
     * @param profilePhotoUrl URL to the profile photo of the user who sent the message
     * @param alignment Message alignment
     */
    public MessageComponent(String messageString, String profilePhotoUrl, Alignment alignment) {
        this.messageString = messageString;
        this.profilePhotoUrl = profilePhotoUrl;
        this.alignment = alignment;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MessageComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
        message.setText(messageString);
        profilePhoto.setFill(new ImagePattern(new Image(profilePhotoUrl)));
        switch (alignment) {
        case RIGHT:
            setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            break;
        case LEFT:
        default:
            setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }
}
