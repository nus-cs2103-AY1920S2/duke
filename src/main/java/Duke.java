import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        System.out.print(createGreeting());
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        while (true) {
            String cmd = sc.nextLine();
            if (cmd.equalsIgnoreCase("bye")) {
                System.out.print(formatReply("Bye. Hope to see you again soon!"));
                sc.close();
                return;
            } else if (cmd.equalsIgnoreCase("list")) {
                System.out.print(formatReply(list(tasks)));
            } else {
                tasks.add(new Todo(cmd));
                System.out.print(formatReply("added: " + cmd));
            }
        }
    }

    private static String list(List<Task> tasks) {
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(counter);
            sb.append(". ");
            sb.append(task);
            sb.append("\n");
            counter += 1;
        }
        return sb.toString();
    }

    private static String createGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("\n");
        sb.append(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
        return sb.toString();
    }

    private static String formatReply(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        String lineBreak = "===========================================================\n";
        for (String line : lines) {
            sb.append("> ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(lineBreak);
        return sb.toString();
    }

}
