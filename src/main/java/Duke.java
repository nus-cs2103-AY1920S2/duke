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

    public static void addTask(String input, String type) {
        String taskCounter = "\nCurrent number of task(s): ";

        if (type.equals("T")) {
            Todo todo = new Todo(input);
            taskList.add(todo);
            replyUser("The following task has been added:\n    " + todo.toString() + taskCounter + taskList.size());
        } else if (type.equals("D")) {
            String[] arr = input.split("/", 2);
            String description = arr[0];
            String by = arr[1].split(" ", 2)[1];
            Deadline deadline = new Deadline(description, by);
            taskList.add(deadline);
            replyUser("The following task has been added:\n    " + deadline.toString() + taskCounter + taskList.size());
        } else if (type.equals("E")) {
            String[] arr = input.split("/", 2);
            String description = arr[0];
            String at = arr[1].split(" ", 2)[1];
            Event event = new Event(description, at);
            taskList.add(event);
            replyUser("The following task has been added:\n    " + event.toString() + taskCounter + taskList.size());
        }
    }

    // Prints the user's task list.
    public static void showList() {
        if (taskList.isEmpty()) {
            replyUser("Your list is empty.");
        } else {
            System.out.println("\n------------------------------------------");
            System.out.println("Here is your list of tasks: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                System.out.println(index + "." + task.toString());
            }
            System.out.println("------------------------------------------\n");
        }
    }

    // Mark the specified task as done.
    public static void markTaskAsDone(String userIndex) {
        int index = Integer.parseInt(userIndex) - 1;
        Task t = taskList.get(index);
        t.markAsDone();
        replyUser("As per requested, the following task has been marked as done:\n" + "    " + t.toString());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        System.out.println("Booting up...\n" + logo);
        System.out.println("How can I serve you?\n");

        while (true) {
            String command = sc.nextLine();
            String[] commandArray = command.split(" ", 2);

            if (command.toLowerCase().equals("bye")) {
                replyUser("I believe this is farewell, my friend.");
                break;
            } else if (command.toLowerCase().equals("list")) {
                showList();
            } else if (commandArray[0].toLowerCase().equals("done")) {
                markTaskAsDone(commandArray[1]);
            } else if (commandArray[0].toLowerCase().equals("todo")) {
                addTask(commandArray[1], "T");
            } else if (commandArray[0].toLowerCase().equals("deadline")) {
                addTask(commandArray[1], "D");
            } else if (commandArray[0].toLowerCase().equals("event")) {
                addTask(commandArray[1], "E");
            } else {
                replyUser("Apologies, I do not recognise this command.");
            }
        }
    }
}
