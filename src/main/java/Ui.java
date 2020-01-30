import java.util.Scanner;

public class Ui {
    private static final String separator = "------------------------------------------";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void hello() {
        System.out.println("Booting up...");
        System.out.println(logo);
        System.out.println("Greetings, I am Duke.");
    }

    public static void printWithBorder(String text) {
        System.out.println("\n" + separator);
        System.out.println(text);
        System.out.println(separator + "\n");
    }

    public static void bye() {
        printWithBorder("I believe this is farewell, my friend.");
    }

    public static void newUser() {
        printWithBorder("Welcome, new user.\n\nHow can I help you today?");
    }

    public static void oldUser() {
        printWithBorder("Welcome back.\n\nHow can I help you today?");
    }

    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
