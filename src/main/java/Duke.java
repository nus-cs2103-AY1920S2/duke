import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        boolean isRunning  = true;
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        while (isRunning) {
            String input = scanner.nextLine();
            String[] commandLine = input.split(" ", 2);
            String command = commandLine[0];
            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                isRunning = false;
            } else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("     %d. %s\n", (i + 1), tasks.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else if (command.equals("done")) {
                int taskNum = Integer.parseInt(commandLine[1]);
                Task taskToMark = tasks.get(taskNum - 1);
                taskToMark.markDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:");
                System.out.printf("     %s\n", taskToMark);
                System.out.println("    ____________________________________________________________");
            } else if (command.equals("todo")){
                Task newTask = new Todo(commandLine[1]);
                tasks.add(newTask);
                System.out.println("    ____________________________________________________________");
                System.out.printf("     Got it. I've added this task: \n       %s\n", newTask);
                System.out.printf("     Now you have %d tasks in the list.\n", tasks.size());
                System.out.println("    ____________________________________________________________");
            } else if (command.equals("deadline")){
                String[] deadlineDescriptionDate = commandLine[1].split(" /by ");
                Task newTask = new Deadline(deadlineDescriptionDate[0], deadlineDescriptionDate[1]);
                tasks.add(newTask);
                System.out.println("    ____________________________________________________________");
                System.out.printf("     Got it. I've added this task: \n       %s\n", newTask);
                System.out.printf("     Now you have %d tasks in the list.\n", tasks.size());
                System.out.println("    ____________________________________________________________");
            } else if (command.equals("event")){
                String[] eventDescriptionDate = commandLine[1].split(" /at ");
                Task newTask = new Event(eventDescriptionDate[0], eventDescriptionDate[1]);
                tasks.add(newTask);
                System.out.println("    ____________________________________________________________");
                System.out.printf("     Got it. I've added this task: \n       %s\n", newTask);
                System.out.printf("     Now you have %d tasks in the list.\n", tasks.size());
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
