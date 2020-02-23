import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Run Main.
     * @param args the loaded file if applicable.
     */
    public static void main(String[] args) {
        //Application.launch(Duke.class, args);
        Application.launch(Main.class, args);

    }
}