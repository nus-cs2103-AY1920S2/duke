import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "    ________________________________________________________";
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + horizontalLine);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(horizontalLine + "\n");

        String[] command = sc.nextLine().split(" ", 2);
        while (!command[0].equals("bye")) {
            System.out.println("\n" + horizontalLine);
            if (command[0].equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks.get(i));
                }
            } else if (command[0].equals("done")) {
                Task task = tasks.get(Integer.parseInt(command[1]) - 1);
                task.markAsDone();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + task);
            } else {
                Task task = null;
                if (command[0].equals("todo")) {
                    task = new Todo(command[1]);
                } else if (command[0].equals("deadline")) {
                    String[] info = command[1].split(" /by ");
                    task = new Deadline(info[0], info[1]);
                } else if (command[0].equals("event")) {
                    String[] info = command[1].split(" /at ");
                    task = new Event(info[0], info[1]);
                }
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + task);
                tasks.add(task);
                System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            }
            System.out.println(horizontalLine + "\n");
            command = sc.nextLine().split(" ", 2);
        }

        System.out.println("\n" + horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine + "\n");
    }
}
