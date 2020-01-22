import java.util.Scanner;

public class Duke {
    /**
     * Add the ability to store whatever text entered by the user and display them
     * back to the user when requested.
     * Add the ability to mark tasks as done.
     */
    static void horizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        boolean isTrue = true;
        Task[] list = new Task[100];
        int currentTask = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Duke.horizontalLine();
        System.out.println("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?");
        Duke.horizontalLine();

        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split(" ", 2);
            Duke.horizontalLine();
            Task newTask;
            try {
                switch (input[0]) {
                    case "bye":
                        System.out.println("\tBye. Hope to see you again soon!");
                        isTrue = false;
                        break;
                    case "list":
                        System.out.println("\tHere are the tasks in your list:");
                        int taskNumber = 1;
                        while (list[taskNumber - 1] != null) {
                            System.out.println("\t" + taskNumber + ". " + list[taskNumber - 1]);
                            taskNumber++;
                        }
                        break;
                    case "done":
                        int doneTaskNumber = Integer.parseInt(input[1]);
                        if (doneTaskNumber > currentTask) {
                            throw new DukeException("☹ OOPS!!! There is no such task.");
                        }
                        Task doneTask = list[doneTaskNumber - 1];
                        doneTask.setDone();
                        System.out.println("\tNice! I've marked this task as done: ");
                        System.out.println("\t\t" + list[Integer.parseInt(input[1]) - 1]);
                        break;
                    case "todo":
                        if (input.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        String[] task = input[1].split("/");
                        newTask = new Todo(input[1]);
                        list[currentTask++] = newTask;
                        break;
                    case "deadline":
                        if (input.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] by = input[1].split("/");
                        if (by.length < 2) {
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                        }
                        newTask = new Deadline(by[0], by[1]);
                        list[currentTask++] = newTask;
                        break;
                    case "event":
                        if (input.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        String[] at = input[1].split("/");
                        if (at.length < 2) {
                            throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
                        }
                        newTask = new Event(at[0], at[1]);
                        list[currentTask++] = newTask;
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println("\t" + e);
            }
            Duke.horizontalLine();
        }
    }
}

