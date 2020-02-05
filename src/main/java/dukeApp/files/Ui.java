package dukeApp.files;

import dukeApp.parse.Parse;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {}
    /**
     * Accept user inputs
     * @param tasks list of task retrieved from file
     */
    public void input(TaskList tasks) throws DukeException {
        sc = new Scanner(System.in);
        String statement = sc.nextLine();

        while (!statement.equals("bye")) {
            Parse parse = new Parse(statement);
            parse.decode(tasks);
            statement = sc.nextLine();
        }
    }

    /**
     * Prints error message if data from file cannot be loaded
     */
    public void showLoadingError() {
        System.out.println("File not found");
    }
}
