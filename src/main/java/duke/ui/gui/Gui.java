package duke.ui.gui;

import duke.ui.UiText;
import javafx.stage.Stage;

public interface Gui extends UiText {
        /** Starts the UI (and the App).  */
        void start(Stage primaryStage);

}
