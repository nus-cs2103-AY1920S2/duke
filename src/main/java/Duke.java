import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        printMessage("Greetings! I'm Duke!\n\tHow may I help you?");

        String cmd = readNextCommand();
        while (true) {
            try {
                String[] cmdSplit = cmd.split(" ", 2);

                if (cmdSplit[0].equals("list")) {
                    displayList(tasks);
                } else if (cmdSplit[0].equals("done")) {
                    int index = Integer.valueOf(cmdSplit[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new DukeException("☹ OOPS!!! Task number out of range!");
                    } else {
                        Task task = tasks.get(index);
                        task.markAsDone();
                        printMessage("Nice! I've marked this task as done:\n\t" + task.toString());
                    }
                } else if (cmdSplit[0].equals("delete")) {
                    int index = Integer.valueOf(cmdSplit[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new DukeException("☹ OOPS!!! Task number out of range!");
                    } else {
                        Task taskToDelete = tasks.get(index);
                        tasks.remove(index);
                        printMessage("Noted! I've removed this task:\n\t\t" + taskToDelete.toString() + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                    }
                } else if (cmdSplit[0].equals("deadline")) {
                    if (cmdSplit.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty");
                    } else {
                        String[] arguments = cmdSplit[1].split(" /by ", 2);
                        if (arguments.length < 2) {
                            throw new DukeException("☹ OOPS!!! Missing deadline parameters!");
                        } else {
                            tasks.add(new Deadline(arguments[0], arguments[1]));
                            printMessage("Got it! I've added the task:\n\t\t" + tasks.get(tasks.size() - 1).toString() + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                        }
                    }
                } else if (cmdSplit[0].equals("event")) {
                    if (cmdSplit.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty");
                    } else {
                        String[] arguments = cmdSplit[1].split(" /at ", 2);
                        if (arguments.length < 2) {
                            throw new DukeException("☹ OOPS!!! Missing event parameters!");
                        } else {
                            tasks.add(new Event(arguments[0], arguments[1]));
                            printMessage("Got it! I've added the task:\n\t\t" + tasks.get(tasks.size() - 1).toString() + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                        }

                    }

                } else if (cmdSplit[0].equals("todo")) {
                    tasks.add(new Todo(cmdSplit[1]));
                    printMessage("Got it! I've added the task:\n\t\t" + tasks.get(tasks.size() - 1).toString() + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                } else if (cmdSplit[0].equals("bye")) {
                    printMessage("Bye! Hope you visit again soon!");
                    break;
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                cmd = readNextCommand();
            } catch (DukeException ex) {
                printMessage(ex.getMessage());
                cmd = readNextCommand();
            }
        }
    }

    public static String readNextCommand() {
        return sc.nextLine();
    }

    public static void printMessage(String msg) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + msg);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void displayList(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                result.append(String.format("%d. %s\n\t", i + 1, tasks.get(i)));
            } else {
                result.append(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }

        printMessage(result.toString());
    }
}
