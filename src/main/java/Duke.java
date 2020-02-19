import e0148811.duke.DukeException;
import e0148811.duke.Logic;
import e0148811.duke.Parser;
import e0148811.duke.Storage;
import e0148811.duke.TaskList;
import e0148811.duke.Ui;

public class Duke {
    private boolean isClosed = false;
    private Logic logic;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private static String readFileMessage;

    public Duke() {
        ui = new Ui();
        Storage storage = new Storage("data/duke.txt", ui);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            readFileMessage = ui.returnLoadingError();
            tasks = new TaskList();
        } finally {
            assert tasks != null;
            if (tasks.getList().size() == 0) {
                readFileMessage = ui.returnFoundEmptyFile();
            } else {
                readFileMessage = ui.returnLoadingSuccess();
            }
            logic = new Logic(storage, tasks, ui);
            ui.linkToTaskList(tasks);
        }
    }

    public static String getReadFileMessage() {
        return readFileMessage;
    }

    public static void main(String[] args) {
        new Duke();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        if (input.equals("bye") || input.equals("b")) {
            isClosed = true;
            return ui.sayGoodbye();
        }
        String[] instructionByWords = parser.parse(input);
        return logic.execute(instructionByWords);
    }

    public boolean isClosed() {
        return isClosed;
    }
}