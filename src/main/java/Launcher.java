import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        // Duke duke = new Duke("data/duke.txt");
        Application.launch(Duke.class, args);
    }
}
