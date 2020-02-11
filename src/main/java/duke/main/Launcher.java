package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        // duke.main.Duke duke = new duke.main.Duke("data/duke.txt");
        Application.launch(Main.class, args);
    }
}
