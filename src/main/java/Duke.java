import java.util.Scanner;

public class Duke {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println();

        printMessage("Hello! I'm Duke!\n\tWhat can I do for you?");

        String cmd = readNextCommand();
        while (!cmd.equals("bye")) {
            printMessage(cmd);
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
}
