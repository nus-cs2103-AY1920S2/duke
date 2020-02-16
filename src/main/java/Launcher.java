import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    public static void main(String[] args) {
//        if (args.length > 0 && args[0].equals("gui")) {
//            Application.launch(Main.class, args);
//        } else {
//            new Duke().run();
//        }
        Application.launch(Main.class, args);
    }

}