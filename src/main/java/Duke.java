import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();

        out("Hello! I'm Duke", "What can I do for you?");
        input: while (in.hasNext()) {
            String next = in.next();
            switch (next) {
                case "bye":
                    out("Bye. Hope to see you again soon!");
                    break input;
                case "list":
                    out(tasks.list());
                    break;
                case "done":
                    out(tasks.done(in.nextInt()));
                    break;
                case "todo":
                    {
                        String[] params = in.nextLine().split("/");
                        out(tasks.addTodo(params[0]));
                    }
                    break;
                case "deadline":
                    {
                        String[] params = in.nextLine().split(" /by ");
                        out(tasks.addDeadline(params[0], params[1]));
                    }
                    break;
                case "event":
                    {
                        String[] params = in.nextLine().split(" /at ");
                        out(tasks.addEvent(params[0], params[1]));
                    }
                    break;
                default:
                    out("invalid command:", echo(next), "please try again");
                    break;
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
