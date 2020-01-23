import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Duke.runDuke();
    }

    // Echoes commands input by the user
    // Terminates when user gives exit signal
    private static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextInput = scanner.nextLine();
            // Validation check
            if (nextInput.equals("bye")) {
                Duke.stopDuke();
                break;
            }
            // Echo user command
            System.out.println("    " + nextInput);
        }
    }

    // Print a closing message before stopping Duke
    private static void stopDuke() {
        String closingMessage = "Bye. Hope to see you again soon!";
        System.out.println("    " + closingMessage);
    }
}
