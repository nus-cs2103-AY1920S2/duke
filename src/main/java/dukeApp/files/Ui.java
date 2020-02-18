package dukeApp.files;

import dukeApp.parse.Parse;

public class Ui {
    public Ui() {}

    /**
     * Accept user inputs
     * @param tasks list of task retrieved from file
     * @param input user input
     * @return message in response to the user input
     * @throws DukeException
     */
    public String input(TaskList tasks, String input) throws DukeException {
        if (!input.equals("bye")) {
            Parse parse = new Parse(input);
            return parse.decode(tasks);
        }
        else {
            return "Bye. Hope to see you again soon!";
        }
    }

    /**
     * Prints error message if data from file cannot be loaded
     * @return error message
     */
    public String showLoadingError() {
        return "File not found";
    }
}
