/**
 * A launcher class to workaround classpath issues.
 */

package duke;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}