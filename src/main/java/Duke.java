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
        while (!cmd.equals("bye")) {
            String[] cmdSplit = cmd.split(" ", 2);

            if (cmdSplit[0].equals("list")) {
                displayList(tasks);
            } else if (cmdSplit[0].equals("done")) {
                Task task = tasks.get(Integer.valueOf(cmdSplit[1]) - 1);
                task.markAsDone();
                printMessage("Nice! I've marked this task as done:\n\t" + task.toString());
            } else {
                Task newTask = null;
                if (cmdSplit[0].equals("deadline")) {
                    String[] arguments = cmdSplit[1].split(" /by ", 2);
                    newTask = new Deadline(arguments[0], arguments[1]);
                } else if (cmdSplit[0].equals("event")) {
                    String[] arguments = cmdSplit[1].split(" /at ", 2);
                    newTask = new Event(arguments[0], arguments[1]);
                } else if (cmdSplit[0].equals("todo")) {
                    newTask = new Todo(cmdSplit[1]);
                }

                tasks.add(newTask);
                printMessage("Got it! I've added the task:\n\t\t" + newTask.toString() + "\n\tNow you have " + tasks.size() + " tasks in the list.");
            }
            cmd = readNextCommand();
        }

        printMessage("Bye! Hope you visit again soon!");
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
