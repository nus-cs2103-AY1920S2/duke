import java.util.Scanner;

public class Ui {

    Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    static String logo = " ____        _        \n\t"
            + "|  _ \\ _   _| | _____ \n\t"
            + "| | | | | | | |/ / _ \\\n\t"
            + "| |_| | |_| |   <  __/\n\t"
            + "|____/ \\__,_|_|\\_\\___|\n\t";

    /**
     * Greetings to be printed.
     */
    public void greetings() {
        //Greeting
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello from\n\t" + logo);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public String getInput() {
        return in.nextLine();
    }

    /**
     * Prints error while saving.
     */
    public void show_error_while_saving() {
        System.out.println("\t_____________________________________");
        System.out.println("\tCouldn't save file.");
        System.out.println("\t_____________________________________");
    }

    /**
     * Prints error when there is no description.
     */
    public void descriptionError() {
        System.out.println("\t_____________________________________");
        System.out.println("\tDescription needs a '/' before by/at");
        System.out.println("\t_____________________________________");
    }

    /**
     * Printing goodbye messages.
     */
    public void goodbye() {
        System.out.println("\t_____________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t_____________________________________");
    }

    /**
     * Print error in the command.
     */
    public void commandError() {
        System.out.println("\t_____________________________________");
        System.out.println("\tPlease enter a valid command: todo, deadline, event, list, bye, find, delete.");
        System.out.println("\t_____________________________________");
    }

    /**
     * Print error message when task is invalid.
     */
    public void invalidTaskError() {
        System.out.println("\t_____________________________________");
        System.out.println("\tTask doesn't exist. Please choose another.");
        System.out.println("\t_____________________________________\n\n");
    }

}
