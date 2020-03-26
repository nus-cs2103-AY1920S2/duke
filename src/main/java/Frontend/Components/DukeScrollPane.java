package Frontend.Components;

import Frontend.Constants.Styles;
import javafx.scene.control.ScrollPane;

public class DukeScrollPane extends ScrollPane {

    public DukeScrollPane( DialogContainer container ){
        this.setContent( container );

        this.setPrefSize( Styles.SCROLL_PANE_WIDTH, Styles.SCROLL_PANE_HEIGHT );
        this.setHbarPolicy( Styles.SCROLL_PANE_SCROLL_X );
        this.setVbarPolicy( Styles.SCROLL_PANE_SCROLL_Y );

        this.setPadding( Styles.SCROLL_PANE_PADDING );

        this.setVvalue( Styles.SCROLL_PANE_VVALUE );
        this.setFitToWidth( Styles.SCROLL_PANE_FIT_TO_WIDTH );
    }

}
