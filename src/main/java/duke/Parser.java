package duke;

/**
 * Parser handles user input.
 */
public class Parser {
    protected String input;
    protected TaskList taskList;
    protected Ui ui = new Ui();

    /**
     * Constructor that takes in user input and loaded task list.
     *
     * @param input
     * @param taskList
     */
    public Parser(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    /**
     * Method to determine action based on user input.
     */
    public String readCommand() {
        String arr[] = this.input.split(" ", 2);
        String response = "";

        if (input.equals("bye")) {
            response = ui.showGoodbye();
            System.exit(0);

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
     * Method to check if user enters a valid input.
     *
     * @param action
     * @throws DukeException
     */
    public static void checkAction(String action) throws DukeException {
        Ui ui = new Ui();

        if (!action.equals("todo") && !action.equals("deadline") && !action.equals("event")) {
            throw new DukeException(ui.showActionError());
        }
    }
}
