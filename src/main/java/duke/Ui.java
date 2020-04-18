package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeTerminateException;
import duke.exceptions.InvalidInputException;

import java.util.Scanner;

/**
 * This is the User Interface of the Duke. Accepts keyboard input from user,
 * and shows the output after the operations.
 */
public class Ui {

    private Scanner sc;
    private Parser parser;

    /**
     * Ui Constructor. Has a Parser attribute to process inputs,
     * and a Scanner to accept inputs. Shows a logo to greet the user.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.parser = new Parser();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Starts the UI. The UI will continue to receive inputs, and terminates
     * when the user wishes to.
     *
     * @param taskList
     */
    public String start(TaskList taskList, String input) {
        //showLine();
        String output = "";
        try {
            String command = input.trim();
            output = parser.processCommand(command, taskList);
        } catch (InvalidInputException e) {
            output = e.toString();
        } catch (DukeException e) {
            output = e.toString();
        } finally {
            return output;
        }
    }

    public String getInput() {
        return this.sc.nextLine();
    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println("============================================================");
    }

    /**
     * Prints a goodbye message to the user upon shutdown.
     */
    public void end() {
        System.out.println("See you again!");
    }
}
