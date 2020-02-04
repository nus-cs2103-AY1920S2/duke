import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Duke chatbot = new Duke("Data/Duke.txt");
        Ui.loadingCompleted();
        Ui.initiateGreetings();
        Application.launch(Main.class, args);
        chatbot.run();
    }
}
