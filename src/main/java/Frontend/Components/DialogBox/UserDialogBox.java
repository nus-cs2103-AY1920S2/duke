package Frontend.Components.DialogBox;

import Frontend.Constants.Styles;
import Frontend.Objects.User;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class UserDialogBox extends DialogBox {

    public UserDialogBox( User user ){
        super( user );

        dialog.setBackground( new Background( new BackgroundFill( Styles.COLOR_BLUE, Styles.DIALOG_RADIUS, Styles.DIALOG_MARGIN ) ) );
        dialog.setTextFill( Styles.COLOR_WHITE );
        displayPicture.setVisible( Styles.DISPLAY_PICTURE_USER_VISIBLE );
        flip();
    }

}
