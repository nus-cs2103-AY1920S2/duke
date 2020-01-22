import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        printMessage("Hello! I'm Duke!\n\tWhat can I do for you?");

        String cmd = readNextCommand();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                displayList(tasks);
            } else {
                tasks.add(cmd);
                printMessage("added: " + cmd);
            }
            cmd = readNextCommand();
        }

        printMessage("Bye! Hope to see you again soon!");
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

    public static void displayList(ArrayList<String> tasks) {
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
