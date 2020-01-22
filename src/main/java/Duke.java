import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static String separator = "____________________________________________________________";
    static List<Task> added = new ArrayList<>();

    /**
     * The main method is where Duke introduces itself
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println(separator + "\nHello! I'm Duke\nWhat can I do for you?\n" + separator);
        handleInput();
    }

    /**
     * Handles input from user.
     */
    private static void handleInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            StringBuilder sb = new StringBuilder(separator + "\n");
            if (input.equals("list")) {
                sb.append("Here are the tasks in your list:\n");
                for (int i = 0; i < added.size(); i++) {
                    sb.append(i + 1);
                    sb.append("." + added.get(i));
                    if (i != added.size() - 1) {
                        sb.append("\n");
                    }
                }
            } else if (input.equals("done")) {
                sb.append("Nice! I've marked this task as done:\n");
                Task t = added.get(sc.nextInt());
                t.markAsDone();
                sb.append(t);
            } else {
                StringBuilder tmp = new StringBuilder();
                if (input.equals("todo")) {
                    tmp.append(sc.nextLine().trim());
                    added.add(new Todo(tmp.toString()));
                } else if (input.equals("deadline")) {
                    String[] temp = sc.nextLine().split("/by");
                    tmp.append(temp[0].trim());
                    added.add(new Deadline(tmp.toString(), temp[1]));
                } else {
                    String[] temp = sc.nextLine().split("/at");
                    tmp.append(temp[0].trim());
                    added.add(new Event(tmp.toString(), temp[1]));
                }
                int size = added.size();
                sb.append("Got it. I've added this task:\n  " + added.get(size - 1));
                sb.append("\nNow you have " + size + " tasks in the list.");
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
