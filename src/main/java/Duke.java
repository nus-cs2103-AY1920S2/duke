import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static String separator = "____________________________________________________________";
    static List<String> added = new ArrayList<>();

    /**
     * Accepts input for Duke to handle
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println(separator + "\nHello! I'm Duke\nWhat can I do for you?\n" + separator);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            StringBuilder sb = new StringBuilder(separator + "\n");
            if (input.equals("list")) {
                for (int i = 0; i < added.size(); i++) {
                    sb.append(i + 1);
                    sb.append(". " + added.get(i) + "\n");
                }

            } else {
                added.add(input);
                sb.append("added: " + input + "\n");
            }
            sb.append(separator + "\n");
            System.out.println(sb);
            input = sc.nextLine();
        }

        if (input.equals("bye")) {
            System.out.println(separator + "\nBye. Hope to see you again soon!\n" + separator);
        }
    }
}
