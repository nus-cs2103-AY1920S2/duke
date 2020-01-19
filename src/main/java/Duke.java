import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        output(new String[]{"Hello! I'm Duke\n     What can I do for you?"});
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while(!op.equals("bye")) {
            if (op.equals("list")) {
                show_list(list);
                op = sc.nextLine();
            } else {
                String[] temp = op.split(" ", 2);
                String cmd = temp[0];
                String body = temp[1];
                if (cmd.equals("done")) {
                    Task task = list.get(Integer.parseInt(body) - 1);
                    task.markAsDone();
                    output(new String[]{"Nice! I've marked this task as done:",
                            task.toString()});
                } else if (cmd.equals("todo")) {
                    Task task = new ToDo(body);
                    int size = list.size();
                    output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                                "Now you have " + (size+1) + " tasks in the list."});
                    list.add(task);
                } else if (cmd.equals("deadline")) {
                    String[] task_date = body.split(" /by ");
                    Task task = new Deadline(task_date[0], task_date[1]);
                    int size = list.size();
                    output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                            "Now you have " + (size+1) + " tasks in the list."});
                    list.add(task);
                } else {
                    String[] task_date = body.split(" /at ");
                    Task task = new Event(task_date[0], task_date[1]);
                    int size = list.size();
                    output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                            "Now you have " + (size+1) + " tasks in the list."});
                    list.add(task);

                }
                op = sc.nextLine();

            }
        }
        output(new String[]{"Bye. Hope to see you again soon!"});
    }

    private static void output(String[] op) {
        String divider = "    -------------------------------------------------------";
        System.out.println(divider);
        for (String s: op) {
            System.out.println("     " + s);
        }
        System.out.println(divider);
    }
    private static void show_list(ArrayList<Task> list) {
        String divider = "    -------------------------------------------------------";
        System.out.println(divider);
        System.out.println("     Here are the tasks in your list:");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("     " + (i+1) + ". " + list.get(i).toString());
        }
        System.out.println(divider);
    }
}
