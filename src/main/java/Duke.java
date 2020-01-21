import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        out("Hello! I'm Duke", "What can I do for you?");
        while (in.hasNext()) {
            String next = in.next();
            if (next.equals("bye")) {
                out("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                out(tasks.list());
            } else if (next.equals("done")) {
                out(tasks.done(in.nextInt()));  
            } else {
                out(tasks.add(next));
            }
        }
        in.close();
    }

    private static void out(String... ss) {
        String border = "    ____________________________________________________________";
        System.out.println(border);
        for (String s : ss) {
            System.out.println("    " + s);
        }
        System.out.println(border);
    }

    private static String echo(String s) {
        return s;
    }
}
