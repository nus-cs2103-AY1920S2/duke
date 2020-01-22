import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static String separator = "____________________________________________________________";
    static List<Task> added = new ArrayList<>();

    /**
     * Accepts input for Duke to handle
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println(separator + "\nHello! I'm Duke\nWhat can I do for you?\n" + separator);
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            StringBuilder sb = new StringBuilder(separator + "\n");
            if (input.equals("list")) {
                sb.append("Here are the tasks in your list:\n");
                for (int i = 0; i < added.size(); i++) {
                    Task t = added.get(i);
                    sb.append(i + 1);
                    sb.append(".[" + t.getStatusIcon() + "] "  + t.description);
                }
            } else if (input.equals("done")) {
                sb.append("Nice! I've marked this task as done:\n");
                Task t = added.get(sc.nextInt());
                t.markAsDone();
                sb.append("[" + t.getStatusIcon() + "] " + t.description);
            } else {
                StringBuilder tmp = new StringBuilder(input);
                if (sc.hasNextLine()) {
                    tmp.append(sc.nextLine());
                }
                added.add(new Task(tmp.toString()));
                sb.append("added: " + tmp);
            }
            sb.append("\n" + separator + "\n");
            System.out.println(sb);
            input = sc.next();
        }

        if (input.equals("bye")) {
            System.out.println(separator + "\nBye. Hope to see you again soon!\n" + separator);
        }
    }
}
