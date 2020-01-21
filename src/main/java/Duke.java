import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Scanner object
        Scanner sc = new Scanner(System.in);

        // greeting
        greet();

        // take in user's command
        String command = sc.nextLine();

        // continue processing user's command, as long as command is not bye
        while (!command.equals("bye")) {
            echo(command);
            command = sc.nextLine();
        }

        // exit
        exit();
    }

    // prints greeting to user
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

    // echos user's command
    public static void echo(String comm) {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    " + comm);
        System.out.println("    ------------------------------------------------------------");
    }

    // exits
    public static void exit() {
        System.out.println("    ------------------------------------------------------------");
        System.out.println("    See you later!");
        System.out.println("    ------------------------------------------------------------");
    }
}
