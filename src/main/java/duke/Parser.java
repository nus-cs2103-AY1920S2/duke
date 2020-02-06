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
    public void readCommand() {
        String arr[] = this.input.split(" ", 2);

        if (input.equals("bye")) {
            Storage storage = new Storage("../../../data/duke.txt");
            storage.store(taskList);
            ui.showGoodbye();
            System.exit(0);

        } else if (input.equals("list")) {
            ui.showList(taskList);

        } else if (arr[0].equals("find")) {
            taskList.find(arr);

        } else if (arr[0].equals("done")) {
            taskList.done(arr);

        } else if (arr[0].equals("delete")) {
            taskList.delete(arr);

        } else {
            try {
                checkAction(arr[0]);
                taskList.add(arr);

            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
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
