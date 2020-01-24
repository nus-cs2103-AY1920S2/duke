import java.util.*;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) throws DukeException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        reply("What can I do for you");

        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                handle(command);
            }
            catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }

        }
        reply("Bye. Hope to see you again soon!");
    }

    public static void handle(String string) throws DukeException{
        if (string.equals("list")) {
            printList();
        } else {
            String type = string.split(" ")[0];
            if (type.equals("done") || type.equals("delete")) {
                int taskNo = Integer.parseInt(string.split(" ")[1]);
                if (taskNo > tasks.size() || taskNo <= 0) {
                    throw new DukeException("☹ OOPS!! Not a valid number");
                } else {
                    if (type.equals("done")) {
                        tasks.get(taskNo - 1).markAsDone();
                        doneMessage(tasks.get(taskNo - 1));
                    } else if (type.equals("delete")) {
                        deleteMessage(tasks.get(taskNo - 1));
                        tasks.remove(taskNo - 1);
                    }
                }
            } else {
                // create new task -> add to tasks -> reply
                Task task = createAndAddTask(type, string);
                addMessage(task);

            }
        }

    }

    public static void reply(String string) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + string);
        System.out.println("    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            System.out.print("    " + count + ".");
            task.taskSummary();
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void doneMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.print("    ");
        task.taskSummary();
        System.out.println("    ____________________________________________________________");
    }

    public static void addMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.print("        ");
        task.taskSummary();
        System.out.println("    Now you have " + Task.totalTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");

    }

    public static void deleteMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.print("    ");
        task.taskSummary();
        System.out.println("    Now you have " + (Task.totalTasks - 1) +  " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static Task createAndAddTask(String type, String whole) throws DukeException {
        Task task;
        if (whole.split(" ").length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }

        if (type.equals("todo")) {
            String desc = whole.substring(5);
            task = new ToDo(desc);

        } else if (type.equals("event")) {
            String desc = whole.substring(6).split("/at ")[0];
            String at = whole.substring(6).split("/at ")[1];
            task = new Event(desc, at);

        } else if (type.equals("deadline")) {
            String desc = whole.substring(9).split("/by ")[0];
            String by = whole.substring(9).split("/by ")[1];
            task = new Deadline(desc, by);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        return task;
    }
}
