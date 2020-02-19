package Frontend.Components.DialogBox;

import java.io.IOException;

import Frontend.Components.MainWindow;
import Frontend.Constants.Styles;
import Frontend.Objects.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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

    public DialogBox( User user) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader( MainWindow.class.getResource( Styles.DIALOG_BOX_FXML ));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogContainer.setPrefWidth( Styles.DIALOG_CONTAINER_WIDTH );

        displayPictureContainer.setAlignment( Styles.DISPLAY_PICTURE_CONTAINER_ALIGNMENT );

        displayPicture.setRadius( Styles.DISPLAY_PICTURE_RADIUS );

        dialog.setText( user.getText() );
        dialog.setBackground( new Background( new BackgroundFill( Styles.COLOR_WHITE, Styles.DIALOG_RADIUS, Styles.DIALOG_MARGIN ) ) );
        dialog.setPadding( Styles.DIALOG_PADDING );

        dialog.setAlignment( Styles.DIALOG_ALIGNMENT );
        dialog.setTextFill( Styles.COLOR_BLACK );
        dialog.setWrapText( Styles.DIALOG_WRAP_TEXT );


    }



    protected void flip(){
        dialogContainer.setAlignment( Pos.TOP_RIGHT );
    }

}