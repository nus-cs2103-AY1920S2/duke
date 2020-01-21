import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // ArrayList of tasks
    private static ArrayList<String> arrList;

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
            if (command.equals("list")) {
                // prints tasks in list if command is list
                printList();
            } else {
                // add task to list
                add(command);
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
     * @param comm command given by user
     */
    public static void add(String comm) {
        arrList.add(comm);
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    added: " + comm);
        System.out.println("    ------------------------------------------------------------");
    }

    /**
     * prints list of tasks user has added
     */
    public static void printList() {
        System.out.println("    ------------------------------------------------------------");
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
