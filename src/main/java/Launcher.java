import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    // @@author FeliciaTay--reused
    // Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
