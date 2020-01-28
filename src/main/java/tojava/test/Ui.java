package tojava.test;
import tojava.parse.Parse;
import java.util.Scanner;

public class Ui {
    Scanner sc;
    String errorMsg;

    public Ui() {}

    /**
     * Accept user inputs
     * @param tasks list of task retrieved from file
     */
    public void input(TaskList tasks) {
        sc = new Scanner(System.in);
        Parse parse = new Parse(sc.nextLine());

        while (!parse.decode(tasks)) {
            parse = new Parse(sc.nextLine());
        }
    }

    /**
     * Prints error message if data from file cannot be loaded
     */
    public void showLoadingError() {
        System.out.println("File not found");
    }
}
