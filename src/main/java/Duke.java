import liaomeng.duke.DukeException;
import liaomeng.duke.Logic;
import liaomeng.duke.Parser;
import liaomeng.duke.Storage;
import liaomeng.duke.TaskList;
import liaomeng.duke.Ui;

/**
 * The class that represents an instance of the task manager.
 */
public class Duke {
    private boolean isClosed = false;
    private Logic logic;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private static String readFileResultMessage;

    /**
     * Initialises an instance of the task manager. During the initialisation,
     * the task manager will attempt to read stored data. If there is stored data,
     * the TaskList class will start with a task list containing the tasks stored.
     * Otherwise, it starts with an empty task list.
     */
    public Duke() {
        ui = new Ui();
        Storage storage = new Storage("NUS-Duke.txt", ui);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
            if (tasks.getList().size() == 0) {
                readFileResultMessage = ui.returnFoundEmptyFile();
            } else {
                readFileResultMessage = ui.returnLoadingSuccess();
            }
        } catch (DukeException e) {
            readFileResultMessage = ui.returnLoadingError();
            tasks = new TaskList();
        } finally {
            logic = new Logic(storage, tasks, ui);
            ui.linkToTaskList(tasks);
        }
    }

    /**
     * Returns the result message of the attempt at reading file which stores data about previous tasks.
     */
    public static String getReadFileResultMessage() {
        return readFileResultMessage;
    }

    /**
     * Returns the response of the task manager according to user input.
     * The input is trimmed to ignore leading and trailing space before being parsed.
     *
     * @param input user input.
     */
    String getResponse(String input) {
        input = input.trim();
        if (input.equals("bye") || input.equals("b")) {
            isClosed = true;
            return ui.sayGoodbye();
        }
        String[] instructionByWords = parser.parse(input);
        return logic.execute(instructionByWords);
    }

    /**
     * Returns the boolean indicating whether the application should be closed.
     * The application should be closed when the user says goodbye.
     */
    public boolean isClosed() {
        return isClosed;
    }
}