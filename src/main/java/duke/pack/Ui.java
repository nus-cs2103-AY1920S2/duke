package duke.pack;

import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    public void showLoadingError(DukeException e) {
        System.out.println(e);
    }

    public void showError(DukeException e) {
        System.out.println(e);
    }

    public String receiveInput() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim();
        return command;
    }

    /**
     * prints greeting to user
     */
    public void greet() {
        System.out.println("    ------------------------------------------------------------------");

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

    public void showLine() {
        System.out.println("    ------------------------------------------------------------------");
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
