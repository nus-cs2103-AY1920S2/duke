import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static final String LF = "\n";
    private static final String WELCOME_MSG = "Hello! I'm Duke" + LF + "What can I do for you?" + LF;

    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String line = br.readLine();

                System.out.println(line + LF);
            } catch (IOException e) {

            }
        }
    }
}
