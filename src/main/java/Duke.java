import java.util.ArrayList;
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

        // Create list
        ArrayList<String> list = new ArrayList<>();

        boolean endInput = false;

        // Create Scanner object
        Scanner sc = new Scanner(System.in);
        while (!endInput) {
            // Read user input
            String input = sc.nextLine();
            String lowInput = input.toLowerCase();

            switch (input) {
            case "bye":
                endInput = true;
                break;
            case "list":
                // Print out the list
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + list.get(i));
                }
                System.out.println("\t____________________________________________________________");
                break;
            default:
                list.add(input);
                System.out.println("\t____________________________________________________________");
                System.out.println("\tadded: " + input);
                System.out.println("\t____________________________________________________________");
                break;
            }


        }

        // Exited the loop, print goodbye message
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");


    }
}
