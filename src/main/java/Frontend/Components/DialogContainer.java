package Frontend.Components;

import Frontend.Components.DialogBox.DialogBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DialogContainer extends VBox {
    public DialogContainer(){
        this.setPrefHeight( Region.USE_COMPUTED_SIZE);
    }

    public void addChild( DialogBox dialogBox ){
        this.getChildren().add(dialogBox);
    }
}
