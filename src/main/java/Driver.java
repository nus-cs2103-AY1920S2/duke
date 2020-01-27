import java.util.Scanner;

/*
 * Driver
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The Driver class is the class that contains the
 * main method to receive inputs from the client and
 * passing it to Duke to be processed.</p>
 * @author Mario Lorenzo
 */

public class Driver {

    /**
     * The readCommand method abstracts the input method
     * done by the Scanner object. It reads the whole line
     * of input entered by the client
     * @param scanner The Scanner object to get the input from the client.
     * @return The String object entered by the client.
     */

    public static String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * The main method runs the program.
     * @param args The command line arguments entered into the program.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Duke duke = Duke.start();

            System.out.println("------------------------");
            System.out.println("Hello I'm Duke!");
            System.out.println("Add your command here!\n");

            String command = readCommand(scanner);
            System.out.println("------------------------");
            while (!command.equals("bye")) {
                duke.processCommand(command);
                System.out.println("------------------------");
                command = readCommand(scanner);
            }

            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("------------------------");
        } catch (DukeInvalidTaskFormatException e) {
            System.err.println(e);
        }
    }
}
