package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Parser handles user input.
 */
public class Parser {
    protected String input;
    protected TaskList taskList;
    protected Ui ui = new Ui();

    /**
     * Takes in user input and loaded task list.
     *
     * @param input of user
     * @param taskList loaded from storage
     */
    public Parser(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    /**
     * Determines action based on user input.
     */
    public String readCommand() {
        String[] arr = this.input.split(" ", 2);
        String response = "";

        if (input.equals("Aloha") || input.equals("aloha")) {
            response = ui.showGreeting();

        } else if (input.equals("bye")) {
            response = ui.showGoodbye();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();

        } else if (input.equals("list")) {
            response = ui.showList(taskList);

        } else if (arr[0].equals("find")) {
            response = taskList.find(arr);

        } else if (arr[0].equals("done")) {
            response = taskList.done(arr);

        } else if (arr[0].equals("delete")) {
            response = taskList.delete(arr);

        } else {
            try {
                checkAction(arr[0]);
                response = taskList.add(arr);

            } catch (DukeException ex) {
                return ex.getMessage();
            }
        }

        return response;
    }

    /**
     * Checks if user enters a valid input.
     *
     * @param action Input of user
     * @throws DukeException error
     */
    public static void checkAction(String action) throws DukeException {
        Ui ui = new Ui();

        if (!action.equals("todo") && !action.equals("deadline") && !action.equals("event")) {
            throw new DukeException(ui.showActionError());
        }
    }
}
