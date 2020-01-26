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
    private static final String DONE_CMD = "done";

    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Task> tasks = new ArrayList<>();

        while (true) {
            try {
                String cmd = br.readLine();

                if (cmd.equals(LIST_CMD)) {
                    System.out.println("Here are your tasks:");

                    int len = tasks.size();

                    for (int i = 0; i < len; ++i) {
                        Task t = tasks.get(i);
                        System.out.println("    " + (i + 1) + ". " + t);
                    }

                    System.out.println();
                } else if (cmd.startsWith(DONE_CMD)) {
                    int id = Integer.parseInt(cmd.split(" ")[1]);
                    Task t = tasks.get(id - 1);

                    t.markAsDone();

                    System.out.println("Nice! I've marked this task as done:" + LF + "    " + t + LF);
                } else if (cmd.equals(BYE_CMD)) {
                    System.out.println(BYE_MSG);
                    break;
                } else {
                    tasks.add(new Task(cmd));
                    System.out.println("added: " + cmd + LF);
                }
            } catch (IOException e) {
                System.out.println("Sorry, an error has occurred:");
                e.printStackTrace();
                break;
            }
        }
    }
}
