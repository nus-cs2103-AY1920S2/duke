import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) throws Exception {
        System.out.println("____________________________________________________________");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        run();
    }

    private static void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                if (tasks.size() != 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + "." + tasks.get(i - 1));
                    }
                    System.out.println("____________________________________________________________");
                } else {
                    throw new DukeException("There are no remaining tasks in the list");
                }
            } else {
                if (command.split(" ").length == 1) {
                    String errorMsg = command.split(" ")[0];
                    if (errorMsg.equals("todo") || errorMsg.equals("deadline") || errorMsg.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of a " + errorMsg + " cannot be empty.");
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else if ((command.split(" ")[0]).equals("done")) {
                    int i = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (i >= tasks.size() || i < 0) {
                        throw new DukeException("There is no such task in the list!");
                    } else {
                        tasks.get(i).markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(tasks.get(i));
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    Task t = null;
                    String taskType = command.split(" ")[0];
                    command = command.substring(command.indexOf(" "));
                    switch (taskType) {
                        case "todo":
                            t = new Todo(command);
                            break;
                        case "event":
                            t = new Event(command.split("/")[0], command.split("/")[1]);
                            break;
                        case "deadline":
                            t = new Deadline(command.split("/")[0], command.split("/")[1]);
                            break;
                    }
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    if (tasks.size() == 1) {
                        System.out.println("Now you have " + tasks.size() + " task in the list.");
                    } else {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                    System.out.println("____________________________________________________________");
                }
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}

