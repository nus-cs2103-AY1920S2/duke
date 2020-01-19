import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");


        String[] command = sc.nextLine().split(" ", 2);
        while (!command[0].equals("bye")) {
            System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
            if (command[0].equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks.get(i));
                }
            } else if (command[0].equals("done")) {
                Task task = tasks.get(Integer.parseInt(command[1]) - 1);
                task.markAsDone();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + task);
            } else {
                Task task = new Task(command[0] + " " + command[1]);
                System.out.println("     added: " + command[0] + " " + command[1]);
                tasks.add(task);
            }
            System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");
            command = sc.nextLine().split(" ", 2);
        }

        System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");
    }
}
