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
    private static final String TODO_CMD = "todo";
    private static final String EVENT_CMD = "event";
    private static final String DEADLINE_CMD = "deadline";
    private static final String LIST_CMD = "list";
    private static final String DONE_CMD = "done";

    private static void printAddedTask(Task t, List<Task> tasks) {
        System.out.println("Got it! I've added this task:" + LF + "    " + t + LF
                + "Now, you have " + tasks.size() + " item(s) in your list." + LF);

    }
    public static void main(String[] args) {
        System.out.println(WELCOME_MSG);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Task> tasks = new ArrayList<>();

        while (true) {
            try {
                String cmd = br.readLine();

                if (cmd.equals(LIST_CMD)) {
                    System.out.println("Here are your task(s):");

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
                } else if (cmd.startsWith(TODO_CMD)) {
                    String[] str  = cmd.split("todo ");
                    if (str.length < 2) {
                        throw new DukeException("The description of a todo cannot  be empty");
                    }
                    Task t = new Todo(cmd.split("todo ")[1]);

                    tasks.add(t);

                    printAddedTask(t, tasks);
                } else if (cmd.startsWith(DEADLINE_CMD)) {
                    String temp = cmd.split("deadline ")[1];
                    String name = temp.split(" /by ")[0];
                    String by = temp.split(" /by ")[1];

                    Task t = new Deadline(name, by);

                    tasks.add(t);

                    printAddedTask(t, tasks);
                } else if (cmd.startsWith(EVENT_CMD)) {
                    String temp = cmd.split("event ")[1];
                    String name = temp.split(" /at ")[0];
                    String by = temp.split(" /at ")[1];

                    Task t = new Event(name, by);

                    tasks.add(t);

                    printAddedTask(t, tasks);
                } else if (cmd.equals(BYE_CMD)) {
                    System.out.println(BYE_MSG);
                    break;
                }
            } catch (IOException e) {
                System.out.println("Sorry, an error has occurred:");
                e.printStackTrace();
                break;
            } catch (DukeException e) {
                System.out.println(e + LF);
            }
        }
    }
}
