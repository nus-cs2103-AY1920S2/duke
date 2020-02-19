package Frontend.Components;

import Frontend.Constants.Config;
import Frontend.Constants.Styles;
import javafx.scene.control.Button;

public class SendButton extends Button {

    public SendButton(){
        super( Config.SEND_BUTTON_TEXT );

        this.setStyle("-fx-background-color: #0066e3; -fx-text-fill: #fff; -fx-cursor: hand;");
        this.setPrefWidth( Styles.BUTTON_WIDTH );
    }

}