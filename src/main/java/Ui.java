import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads one line of user input.
     * @return User input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints a welcome message when Duke is initialised.
     */
    public void showWelcome() {
        String greeting = "Hello! I'm Woody and I'm always here to keep you company.\n"
                + "Let me know what you need and I'll be right on it.\n";
        System.out.println(greeting);
    }

    /**
     * Prints a straight line which acts as a border for Duke's reply.
     */
    public void showLine() {
        System.out.println("_______________________________________________________________________");
    }

    /**
     * Prints Duke's reply to the user.
     * @param msg Duke's reply.
     */
    public void showMsg(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints an error message.
     * @param errorMsg Error message.
     */
    public void showError(String errorMsg) {
        System.out.println("OOPS!!! " + errorMsg);
    }

    /**
     * Prints an error message for errors encountered during loading.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! Error when loading tasks from file to Woody.");
    }
}
