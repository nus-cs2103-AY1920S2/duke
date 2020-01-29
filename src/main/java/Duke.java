import java.io.IOException;

public class Duke {
    /**
     * Main driver of the program.
     * @param args Commandline arguments, not used
     */
    public static void main(String[] args) {
        try {
            Ui.run();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
