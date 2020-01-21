import duke.pack.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // ArrayList of tasks
    private static ArrayList<Task> arrList;

    public static void main(String[] args) {
        // Scanner object
        Scanner sc = new Scanner(System.in);

        arrList = new ArrayList<>();

        // greeting
        greet();

        // take in user's command
        String command = sc.nextLine();

        // continue processing user's command, as long as command is not bye
        while (!command.equals("bye")) {
            String[] c = command.split(" ");

            if (command.equals("list")) {
                // prints tasks in list if command is list
                printList();

            } else if (c[0].equals("done")){
                // mark the specified task as done
                int taskNum = Integer.parseInt(c[1]);
                arrList.get(taskNum - 1).markAsDone();

            } else {
                // add task to list
                Task t = new Task(command);
                add(t);
            }

            command = sc.nextLine();
        }

        // exit
        exit();
    }

    /**
     * prints greeting to user
     */
    public static void greet() {
        System.out.println("    ------------------------------------------------------------");

        // logo obtained using https://www.kammerl.de/ascii/AsciiSignature.php
        String logo = "     __  __ _           _          _      \n" +
                "    |  \\/  | |         | |        | |     \n" +
                "    | \\  / | |__   ___ | |_       | |_ __ \n" +
                "    | |\\/| | '_ \\ / _ \\| __|  _   | | '__|\n" +
                "    | |  | | |_) | (_) | |_  | |__| | |   \n" +
                "    |_|  |_|_.__/ \\___/ \\__|  \\____/|_|   \n" +
                "\n";

        System.out.println(logo);
        System.out.println("    Hello, I'm Mbot Jr!\n" +
                "    How may I help you?");
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * echos user's command
     * @param comm command given by user
     */
    public static void echo(String comm) {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    " + comm);
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * adds user's command to arrList
     * @param t command given by user
     */
    public static void add(Task t) {
        arrList.add(t);
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    added: " + t);
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * prints list of tasks user has added
     */
    public static void printList() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Here are your tasks:");

        for (int i = 1; i <= arrList.size(); i++) {
            System.out.println("    " + i + ". " + arrList.get(i - 1));
        }
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * exits
     */
    public static void exit() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    See you later!");
        System.out.println("    ------------------------------------------------------------");
    }
}
