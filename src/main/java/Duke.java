import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<>();

    // Simply adds a separator above and below an intended output.
    public static void replyUser(String output) {
        System.out.println("\n------------------------------------------");
        System.out.println(output);
        System.out.println("------------------------------------------\n");
    }

    // Prints the user's task list.
    public static void showList() {
        if (taskList.isEmpty()) {
            replyUser("Your list is empty!");
        } else {
            System.out.println("\n------------------------------------------");
            System.out.println("Your list of tasks: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                System.out.println(index + ". " + task.getDescription());
            }
            System.out.println("------------------------------------------\n");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        while (true) {
            String command = sc.nextLine();

            if (command.toLowerCase().equals("bye")) {
                replyUser("Hope to see you again!");
                break;
            } else if (command.toLowerCase().equals("list")) {
                showList();
            } else {
                Task newTask = new Task(command);
                taskList.add(newTask);
                replyUser("added: " + command);
            }
        }
    }
}
