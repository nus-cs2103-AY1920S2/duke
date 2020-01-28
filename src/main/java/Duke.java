import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        try {
            Ui.run();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
