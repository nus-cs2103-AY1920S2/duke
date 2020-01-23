import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String t = sc.nextLine();
            String input[] = t.split(" ");
            if (input[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input[0].equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println("There is no task in your list! Please continue...");
                    continue;
                }
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ".  " + tasks.get(i - 1));
                }
                continue;
            } else if (input[0].equals("done")) {
                int idx = Integer.parseInt(input[1]) - 1;
                if (tasks.size() <= idx || idx < 0) {
                    System.out.println("Please key in the correct task index! This index does not match any task.");
                    continue;
                }
                tasks.get(idx).finishTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(idx));
            } else if (input[0].equals("todo")) {
                System.out.println("Got it. I've added this task:");
                Todo todo = new Todo(t.substring(5));
                System.out.println("   " + todo);
                tasks.add(todo);
                System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            } else if (input[0].equals("deadline")) {
                System.out.println("Got it. I've added this task:");
                String deadline = t.split("/")[1].substring(3);
                String task = t.split("/")[0].split(" ", 2)[1];
                Deadline newDeadline = new Deadline(task, deadline);
                System.out.println("   " + newDeadline);
                tasks.add(newDeadline);
                System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            } else if (input[0].equals("event")) {
                System.out.println("Got it. I've added this task:");
                String event = t.split("/")[1].substring(3);
                String task = t.split("/")[0].split(" ", 2)[1];
                Event newEvent = new Event(task, event);
                System.out.println("   " + newEvent);
                tasks.add(newEvent);
                System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            }   else {
                System.out.println("Oops I guess it's an invalid command!! Try again :3");
            }
        }
    }
}