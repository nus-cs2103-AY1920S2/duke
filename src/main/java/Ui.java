import java.util.Scanner;
/**
 * User interface of the application
 */
public class Ui {
    Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a String used for output on GUI
     * @param result The CommandResult representing the result of the command execution
     * @return a String representing the response for the user
     */
    public String getResponse(CommandResult result) {
        return result.getFeedbackToUser();
    }

    public String getUserCommand() {
        return sc.nextLine();
    }

    public void showResultToUser(CommandResult result) {
        System.out.println(result.getFeedbackToUser());
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");
    }



    public void showGoodbyeMessage() {
        System.out.println(":D");
    }

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
