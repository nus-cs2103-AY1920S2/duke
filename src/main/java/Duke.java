import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(createGreeting());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            if (cmd.equalsIgnoreCase("bye")) {
                System.out.println(wrapLineBreak("Bye. Hope to see you again soon!"));
                sc.close();
                return;
            }
            System.out.println(wrapLineBreak(cmd));
        }
    }

    private static String createGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append("Hello from\n");
        sb.append(logo);
        sb.append(wrapLineBreak("Hello! I'm Duke\nWhat can I do for you?"));
        return sb.toString();
    }

    private static String wrapLineBreak(String str) {
        return "____________________________________________________________\n"
            + str
            + "\n"
            + "____________________________________________________________\n";
    }
}
