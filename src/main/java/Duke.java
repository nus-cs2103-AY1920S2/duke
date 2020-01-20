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
            if (input.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                isRunning = false;
            } else if (input.toLowerCase().equals("list")){
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf(" %d. %s\n", (i + 1), tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("____________________________________________________________");
                System.out.printf(" added: %s\n", newTask);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
