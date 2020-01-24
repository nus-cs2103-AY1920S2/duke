import java.util.*;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
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
            handle(command);
        }
        reply("Bye. Hope to see you again soon!");
    }

    public static void handle(String string) {
        if (string.equals("list")) {
            printList();
        } else {
            String type = string.split(" ")[0];
            if (type.equals("done")) {
                int taskNo = Integer.parseInt(string.split(" ")[1]);
                if (taskNo > tasks.size()) {
                    reply("Not a valid number");
                } else {
                    tasks.get(taskNo - 1).markAsDone();
                    doneMessage(tasks.get(taskNo - 1));
                }
            } else {
                // create new task -> add to tasks -> reply
                Task task = createAndAddTask(type, string);
                addMessage(task);
//                if (type.equals("todo")) {
//                    addMessage(task);
//                } else if (type.equals("event")) {
//
//                } else if (type.equals("deadline")) {
//
//                }
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

    public static Task createAndAddTask(String type, String whole) {
        Task task;
        if (type.equals("todo")) {
            String desc = whole.substring(5);
            task = new ToDo(desc);

        } else if (type.equals("event")) {
            String desc = whole.substring(6).split("/at ")[0];
            String at = whole.substring(6).split("/at ")[1];
            task = new Event(desc, at);

        } else {
            String desc = whole.substring(9).split("/by ")[0];
            String by = whole.substring(9).split("/by ")[1];
            task = new Deadline(desc, by);
        }
        tasks.add(task);
        return task;
    }
}
