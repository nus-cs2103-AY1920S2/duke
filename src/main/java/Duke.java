import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<Task> taskList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        taskList = new ArrayList<>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("list")) {
                showList();
            } else if (input.equals("bye")) {
                exit();
                break;
            } else if (input.split(" ")[0].equals("done")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                done(taskNum);
            } else {
                add(input);
            }
        }
    }

    public static void add(String s) {
        String typeOfTask = s.split(" ", 2)[0];
        String task = s.split(" ", 2)[1];
        Task toAdd = new Task();
        if (typeOfTask.equals("todo")) {
            toAdd = new Todo(task);
        } else if (typeOfTask.equals("event")) {
            String[] taskParts = task.split(" /at ");
            toAdd = new Event(taskParts[0], taskParts[1]);
        } else if (typeOfTask.equals("deadline")) {
            String[] taskParts = task.split(" /by ");
            toAdd = new Deadline(taskParts[0], taskParts[1]);
        }
        taskList.add(toAdd);
        System.out.println("Got it. I've added this task:\n"
                + "  " + toAdd +"\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task t : taskList) {
            System.out.println(count + "." + t);
            count++;
        }
    }

    public static void done(int i) {
        taskList.get(i-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: \n" +
                "  " + taskList.get(i-1));
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
