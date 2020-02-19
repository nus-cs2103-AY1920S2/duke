package Frontend.Constants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public final class Styles {
    private Styles() {
    }

    public static final double STAGE_MIN_HEIGHT = 600.0;
    public static final double STAGE_MIN_WIDTH = 400.0;

    public static final double ANCHOR_PANE_WIDTH = STAGE_MIN_WIDTH;
    public static final double ANCHOR_PANE_HEIGHT = STAGE_MIN_HEIGHT;

    public static final double SCROLL_PANE_HEIGHT = STAGE_MIN_HEIGHT - 27;
    public static final double SCROLL_PANE_WIDTH = STAGE_MIN_WIDTH;
    public static final double SCROLL_PANE_TOP_ANCHOR = 1.0;
    public static final ScrollPane.ScrollBarPolicy SCROLL_PANE_SCROLL_X = ScrollPane.ScrollBarPolicy.NEVER;
    public static final ScrollPane.ScrollBarPolicy SCROLL_PANE_SCROLL_Y = ScrollPane.ScrollBarPolicy.ALWAYS;
    public static final Insets SCROLL_PANE_PADDING = new Insets(0, 0, 20, 0);
    public static final double SCROLL_PANE_VVALUE = 1.0;
    public static final boolean SCROLL_PANE_FIT_TO_WIDTH = true;

    public static final double INPUT_WIDTH = 335.0;
    public static final double INPUT_LEFT_ANCHOR = 1.0;
    public static final double INPUT_BOTTOM_ANCHOR = 1.0;

    public static final double BUTTON_WIDTH = STAGE_MIN_WIDTH - INPUT_WIDTH;
    public static final double BUTTON_BOTTOM_ANCHOR = 1.0;
    public static final double BUTTON_RIGHT_ANCHOR = 1.0;

    public static final double LINK_BOTTOM_ANCHOR = 27.5;

    public static final double DIALOG_CONTAINER_WIDTH = STAGE_MIN_WIDTH;
    public static final String DIALOG_BOX_FXML = "/view/DialogBox.fxml";
    public static final CornerRadii DIALOG_RADIUS = new CornerRadii( 10 );
    public static final Insets DIALOG_MARGIN = new Insets( 12 );
    public static final Insets DIALOG_PADDING = new Insets( 24 );
    public static final Pos DIALOG_ALIGNMENT = Pos.TOP_LEFT;
    public static final boolean DIALOG_WRAP_TEXT = true;

    public static final Pos DISPLAY_PICTURE_CONTAINER_ALIGNMENT = Pos.TOP_LEFT;

    public static final Insets DISPLAY_PICTURE_PADDING = new Insets( 12, 0, 0, 8 );
    public static boolean DISPLAY_PICTURE_USER_VISIBLE = false;
    public static double DISPLAY_PICTURE_RADIUS = 24;

    public static final Color COLOR_BLACK = Color.rgb(0,0,0);
    public static final Color COLOR_BLUE = Color.rgb( 0, 102, 227 );
    public static final Color COLOR_TRANSPARENT = Color.TRANSPARENT;
    public static final Color COLOR_WHITE = Color.WHITE;
}
