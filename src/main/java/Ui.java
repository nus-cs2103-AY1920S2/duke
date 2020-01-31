import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ui {
    public static final String LF = "\n";
    private static final String WELCOME_MSG = "Hello! I'm Duke" + LF + "What can I do for you?" + LF;
    private static final String BYE_MSG = "Bye, hope to see you again soon!" + LF;

    private BufferedReader br;

    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printWelcomeMsg() {
        System.out.println(WELCOME_MSG);
    }

    public void printByeMsg() {
        System.out.println(BYE_MSG);
    }

    public String readCmd() throws IOException {
        return br.readLine().trim();
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printLine() {
        System.out.println();
    }
}
