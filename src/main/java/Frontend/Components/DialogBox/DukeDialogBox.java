package Frontend.Components.DialogBox;
import Frontend.Constants.Styles;
import Frontend.Objects.User;
import javafx.scene.paint.ImagePattern;

public class DukeDialogBox extends DialogBox {

    public DukeDialogBox( User user ){
        super(user);

        displayPictureContainer.setPadding( Styles.DISPLAY_PICTURE_PADDING );
        displayPicture.setStroke( Styles.COLOR_TRANSPARENT );
        displayPicture.setFill( new ImagePattern( user.getImage() ) );
    }

}
