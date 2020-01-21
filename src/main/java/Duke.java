import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        System.out.println(addBorders("Hello! I'm Duke\n  What can I do for you?"));
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String next = in.next();
            if (next.equals("bye")) {
                System.out.println(addBorders("Bye. Hope to see you again soon!"));
                break;
            } else {
                System.out.println(addBorders(echo(next)));
            }
        }
        in.close();
    }

    private static String addBorders(String s) {
        String border = "____________________________________________________________";
        return String.format("    %s\n     %s\n    %s", border, s, border);
    }

    public static String echo(String s) {
        return s;
    }
}
