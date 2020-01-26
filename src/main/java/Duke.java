import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String LF = "\n";
    private static final String WELCOME_MSG = "Hello! I'm Duke" + LF + "What can I do for you?" + LF;
    private static final String BYE_MSG = "Bye, hope to see you again soon!" + LF;
    private static final String BYE_CMD = "bye";
    private static final String LIST_CMD = "list";

    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> tasks = new ArrayList<>();

        mainLoop:
        while (true) {
            try {
                String cmd = br.readLine();

                switch (cmd) {
                    case LIST_CMD:
                        System.out.println("Here are your tasks:");

                        for (String task : tasks) {
                            System.out.println(task);
                        }

                        System.out.println();
                        break;
                    case BYE_CMD:
                        System.out.println(BYE_MSG);
                        break mainLoop;
                    default:
                        tasks.add(cmd);
                        System.out.println("added: " + cmd + LF);
                        break;
                }
            } catch (IOException e) {
                System.out.println("Sorry, an error has occurred:");
                e.printStackTrace();
                break;
            }
        }
    }
}
