
import java.util.Scanner;

/**
 * User Interface for interaction with the user, taking in inputs and showing outputs.
 */
public class Ui {

    TaskList mylist;
    Scanner sc = new Scanner(System.in);

    /**
     * Creating a UI with the tasks.
     * @param tasks task list.
     */
    public Ui(TaskList tasks) {

        this.mylist = tasks;

    }

    /**
     * Display welcome message to the user.
     */
    public void showWelcome() {

        System.out.println("Hello! I'm Duke\n"
                + "     What can I do for you?");

    }

    /**
     * Take in input from the user.
     * @return the instruction taken in from the user for parser to parse.
     */
    public String readLine() {

        String response = sc.nextLine();
        return response;

    }

    /**
     * Print formatting lines.
     */
    public void printFormatting() {
        System.out.println("     ____________________________________________________________");
    }

    /**
     * Print error message when an error is encountered.
     * @param ex error that is to be displayed.
     */
    public void showError(DukeException ex) {

        System.out.println(ex.getMessage());


    }



}
