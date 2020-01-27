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
     * The main method runs the program.
     * @param args The command line arguments entered into the program.
     */

    public static void main(String[] args) {
        Ui userInterface = new Ui();
        try {
            Duke duke = Duke.start();
            userInterface.start();

            String command = userInterface.readCommand();
            userInterface.printLine();
            while (!command.equals("bye")) {
                String message = duke.processCommand(command);
                userInterface.displayMessage(message);
                userInterface.printLine();
                command = userInterface.readCommand();
            }

        } catch (DukeInvalidTaskFormatException | DukeInvalidDateFormatException e) {
            userInterface.displayErrorMessage(e);
            userInterface.printLine();
        } finally {
            userInterface.close();
        }
    }
}
