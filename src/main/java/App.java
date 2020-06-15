import javafx.application.Application;
import app.Duke;
import views.Launcher;

final class App {
    public static final boolean HEADLESS = false;

    public static void main(String[] args) {
        if (HEADLESS) {
            (new Duke()).start();
        } else {
            Application.launch(Launcher.class, args);
        }
    }
}