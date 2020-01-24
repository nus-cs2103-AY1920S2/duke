import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        // Print welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        boolean endInput = false;

        // Create Scanner object
        Scanner sc = new Scanner(System.in);
        while (!endInput) {
            // Read user input
            String input = sc.next();
            String lowInput = input.toLowerCase();

            if (lowInput.equals("bye")) {
                // If the input is bye, end the loop and exit to print the goodbye message
                endInput = true;
                break;
            } else {
                // Else, echo the message back to the user
                System.out.println("\t____________________________________________________________");
                System.out.println("\t" + input);
                System.out.println("\t____________________________________________________________");
            }

        }

        // Exited the loop, print goodbye message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }
}
