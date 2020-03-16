import java.util.Scanner;

/**
 * The Ui class is mainly responsible for reading user inputs and printing different output
 * to the user based on his or her input.
 */
class Ui {
    /**
     * Shows the Duke logo when the Duke program is started.
     */
    String showWelcome() {
        return "Hello! I am Tom. What can I do for you today?";
    }

    /**
     * Shows a divider line.
     */
    void showLine() {
        System.out.println(Duke.LINE);
    }

    /**
     * This method reads the input of the user.
     *
     * @return A String identical to the user's input.
     */
    String readCommand() {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        return command;
    }

    void showByeMessage() {
        System.out.println("Bye-bye! See you again, my friend! "
                + "Please close this window if you do not wish to continue this session.");
    }

    void showDoneMessage() {
        System.out.println("\n" + "Fantastic! This task is a done-deal!" + "\n");
    }


    /**
     * Used to print error messages to the user.
     *
     * @param message The error message to be shown.
     */
    void showError(String message) {
        System.out.println(message);
    }

    /**
     * Used to print loading error which only occurs if the .txt file is empty.
     */
    void showLoadingError() {
        System.out.println("The current duke.txt file is empty. Your inputs will be saved into an empty text file.");
    }
}
