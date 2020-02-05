package duke.ui.gui;

import duke.ui.Ui;

// import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher extends Ui {

    private Gui gui;

    public Launcher() {
        this.gui = new Gui();
    }

    public void launch() {
        gui.start(new Stage());
    }

    public void showMessage(String message) {
        
    }
}