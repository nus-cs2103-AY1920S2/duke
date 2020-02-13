package Frontend.Components.DialogBox;
import Frontend.Objects.User;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class DukeDialogBox extends DialogBox {

    public DukeDialogBox( User user ){
        super(user);

        displayPictureContainer.setPadding(displayPicturePadding);
        displayPicture.setStroke( colorTransparent );
        displayPicture.setFill( new ImagePattern( user.getImage() ) );
    }

}
