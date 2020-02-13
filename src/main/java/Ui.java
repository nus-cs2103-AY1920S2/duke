import java.util.Scanner;

public class Ui {
    private static final String separator = "------------------------------------------";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints welcome message.
     */
    public static void hello() {
        System.out.println("Booting up...");
        System.out.println(logo);
        System.out.println("Greetings, I am Duke.");
    }

    /**
     * Prints the input provided with top and bottom separators.
     * @param text Text to be framed.
     */
    public static void printWithBorder(String text) {
        System.out.println("\n" + separator);
        System.out.println(text);
        System.out.println(separator + "\n");
    }

    /**
     * Prints farewell message.
     */
    public static void bye() {
        printWithBorder("I believe this is farewell, my friend.");
    }

    /**
     * Prints message for new user.
     */
    public static void newUser() {
        printWithBorder("Welcome, new user.\n\nHow can I help you today?");
    }

    /**
     * Prints message for old user.
     */
    public static void oldUser() {
        printWithBorder("Welcome back.\n\nHow can I help you today?");
    }

    /**
     * Reads user input using a Scanner class.
     * @return User input.
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
