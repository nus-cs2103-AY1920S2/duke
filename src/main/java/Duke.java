import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        boolean isRunning  = true;
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");

        while (isRunning) {
            String input = scanner.nextLine();
            String[] command = input.split(" ", 2);
            if (command[0].equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                isRunning = false;
            } else if (command[0].equals("list")) {
                System.out.println("____________________________________________________________\n" +
                        " Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf(" %d. %s\n", (i + 1), tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if (command[0].equals("done")) {
                int taskNum = Integer.parseInt(command[1]);
                Task taskToMark = tasks.get(taskNum - 1);
                taskToMark.markDone();
                System.out.println("____________________________________________________________\n" +
                        " Nice! I've marked this task as done:");
                System.out.printf(" %s\n", taskToMark);
                System.out.println("____________________________________________________________");
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("____________________________________________________________");
                System.out.printf(" added: %s\n", newTask.getDescription());
                System.out.println("____________________________________________________________");
            }
        }
    }
}
