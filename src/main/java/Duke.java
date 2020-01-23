import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t" + line);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String next = sc.nextLine();
        while (!next.trim().equals("bye")) {
            System.out.println("\t" + line);
            if (next.trim().equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int i = 1;
                for (Task s: tasks) {
                    System.out.println("\t" + i + "." + s.toString());
                    i++;

                }
            } else if ((next.trim().split(" ")[0]).equals("done")) {
                Integer taskNumber = Integer.valueOf((next.split(" ")[1]));
                Task updatedTask = tasks.get(taskNumber - 1).setDone();
                tasks.set(taskNumber - 1, updatedTask);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t\t" + updatedTask);
            } else {
                tasks.add(new Task(next));
                System.out.println("\tadded: " + next);
            }
            System.out.println("\t" + line);
            next = sc.nextLine();
        }
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}