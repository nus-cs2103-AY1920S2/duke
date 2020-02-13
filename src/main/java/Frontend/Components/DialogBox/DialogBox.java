package Frontend.Components.DialogBox;

import java.io.IOException;
import java.util.Collections;

import Frontend.Components.MainWindow;
import Frontend.Objects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 *
 *    Title: JavaFX Tutorial
 *    Author: Jeffry Lum, Damith C. Rajapakse
 *    Availability: https://github.com/se-edu/duke/blob/master/tutorials
 */
public abstract class DialogBox extends HBox {
    @FXML
    protected HBox dialogContainer;
    @FXML
    protected Label dialog;
    @FXML
    protected Circle displayPicture;
    @FXML
    protected StackPane displayPictureContainer;

    protected final CornerRadii dialogRadius = new CornerRadii( 10 );
    protected final Insets dialogMargin = new Insets(12);
    protected final Insets dialogPadding = new Insets(24);
    protected final Insets displayPicturePadding = new Insets( 12, 0, 0, 8 );
    protected final Color colorBlue = Color.rgb(0, 102, 227 );
    protected final Color colorTransparent = Color.TRANSPARENT;
    protected final Color colorWhite = Color.WHITE;

    public DialogBox( User user) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader( MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText( user.getText() );
        dialog.prefHeight( Region.USE_COMPUTED_SIZE );
        dialog.setBackground( new Background( new BackgroundFill( colorWhite, dialogRadius, dialogMargin ) ) );
        dialog.setPadding(dialogPadding);

    }



    protected void flip(){
        dialogContainer.setAlignment( Pos.TOP_RIGHT );
    }

}