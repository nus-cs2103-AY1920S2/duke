import java.util.Scanner;

/**
 * Represents a ui object that deals with interactions with the user.
 */
public class Ui {

    Scanner sc;

    /**
     * Constructor for ui object.
     */
    public Ui() {
        Scanner scanner = new Scanner(System.in);
        this.sc = scanner;
    }

    /**
     * Method to print intro message.
     */
    public void showWelcome() {
        String intro = "--------------------------------------------------\n" +
                "Wassup! Wo shi bot \n" +
                "You want what? \n" +
                "--------------------------------------------------\n";

        System.out.print(intro);
    }

    /**
     * Method to print termination message.
     */
    public void goodBye() {
        String goodbye = "--------------------------------------------------\n" +
                "Why you so ba...bot has been killed\n" +
                "--------------------------------------------------\n";

        System.out.print(goodbye);
    }

    /**
     * Method to print loading file error.
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     * Method to print exception error message.
     * @param e exception
     */
    public void showError(Exception e) {
        System.out.println(e.toString());
    }

    /**
     * Method to read input from user.
     * @return input read
     */
    public String readInput() {
        String input = sc.nextLine();
        return input;
    }
}
