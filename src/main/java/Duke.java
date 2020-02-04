import command.Controller;
import command.DukeException;
import command.Storage;
import command.UI;

import java.util.Scanner;

/**
 * The project is a product named Duke, a Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 */
public class Duke {

    /**
     * Print the output with horizontal lines for formatting.
     *
     * @param output output to be printed.
     */
    static void printOutput(String output) {
        System.out.println("\t____________________________________________________________\n"
                + output + "\n\t____________________________________________________________");
    }

    /**
     * Start the chat bot by first retrieving saved file from hard disk then
     * obtaining input from the user. Saves the task list into the storage file
     * when user terminates the programme.
     *
     * @param args user input.
     */
    public static void main(String[] args) {
        System.out.println(UI.START);
        printOutput(UI.HELLO);
        Scanner sc = new Scanner(System.in);
        Storage.readFromFile();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = sc.nextLine();
                String output = Controller.readInput(input);
                if (output.equals(UI.BYE)) {
                    isRunning = false;
                }
                printOutput(output);
            } catch (DukeException e) {
                System.err.println(e);
            }
        }
        Storage.saveFile();
    }
}

