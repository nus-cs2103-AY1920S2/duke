import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "     -----------------------------------------------------------------\n";
        String tab = "      ";
        String greet = line
                + tab + "Hello! I'm Duke\n"
                + tab + "What can I do for you?\n"
                + line;
        System.out.println(greet);

        ArrayList<Task> list = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);

        while (!input.hasNext("bye")) {
            String next = input.nextLine();
            if (next.contains("done")) {
                String[] sub = next.split(" ");
                list.get(parseInt(sub[1])).markAsDone();
                System.out.println(line + tab + "Nice! I've marked this task as done: \n" +
                        tab + list.get(parseInt(sub[1])) + "\n" + line);
            } else if (next.contains("list")) {
                System.out.print(line + tab + "Here are the tasks in your list: \n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(tab + list.get(i));
                }
                System.out.print(line);
            } else if (next.contains("todo")) {
                Task toDo = new ToDo(next.replace("todo", ""));
                list.add(toDo);
                System.out.println(line + tab + "Got it. I've added this task:");
                System.out.println(tab + toDo);
                System.out.println(tab + "Now you have " + list.size() + " tasks in the list.\n" + line);
            } else if (next.contains("deadline")) {
                String deadline = next.split("/")[1];
                String description = next.split("/")[0].replace("deadline ", "");
                Task dlTask = new Deadline(description, deadline);
                list.add(dlTask);
                System.out.println(line + tab + "Got it. I've added this task:");
                System.out.println(tab + dlTask);
                System.out.println(tab + "Now you have " + list.size() + " tasks in the list.\n" + line);
            } else if (next.contains("event")) {
                String time = next.split("/")[1];
                String description = next.split("/")[0].replace("event ", "");
                Task eventTask = new Event(description, time);
                list.add(eventTask);
                System.out.println(line + tab + "Got it. I've added this task:");
                System.out.println(tab + eventTask);
                System.out.println(tab + "Now you have " + list.size() + " tasks in the list.\n" + line);
            } else {
                Task t = new Task(next);
                System.out.println(line + "     added: " + t.getDescription() + "\n" + line);
                list.add(t);
            }
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}