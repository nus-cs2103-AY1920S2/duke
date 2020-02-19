package Frontend.Components;

import Frontend.Constants.Styles;
import javafx.scene.layout.AnchorPane;

public class DukeAnchorPane extends AnchorPane {

    DukeScrollPane scrollPane;
    DukeInput input;
    SendButton button;
    DukeLink link;

    public DukeAnchorPane(
            DukeScrollPane scrollPane,
            DukeInput input,
            SendButton button,
            DukeLink link
    ){
        this.setPrefSize( Styles.ANCHOR_PANE_WIDTH, Styles.ANCHOR_PANE_HEIGHT );

        this.scrollPane = scrollPane;
        this.input = input;
        this.button = button;
        this.link = link;

        this.getChildren().addAll( scrollPane, input, button, link );

        setTopAnchor(scrollPane, Styles.SCROLL_PANE_TOP_ANCHOR );

        setBottomAnchor(button, Styles.BUTTON_BOTTOM_ANCHOR );
        setRightAnchor(button, Styles.BUTTON_RIGHT_ANCHOR );

        setBottomAnchor( link, Styles.LINK_BOTTOM_ANCHOR );

        setLeftAnchor(input , Styles.INPUT_LEFT_ANCHOR );
        setBottomAnchor(input, Styles.INPUT_BOTTOM_ANCHOR );

    }

}
