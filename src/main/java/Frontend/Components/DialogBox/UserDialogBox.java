package Frontend.Components.DialogBox;

import Frontend.Objects.User;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class UserDialogBox extends DialogBox {

    public UserDialogBox( User user ){
        super( user );

        dialog.setBackground( new Background( new BackgroundFill(colorBlue, dialogRadius, dialogMargin ) ) );
        dialog.setTextFill( colorWhite );
        displayPicture.setVisible( false );
        flip();
    }

}
