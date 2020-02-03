import dukeexception.DukeException;
import dukeexception.DukeUnknownInputException;

/**
 * Duke class uses Storage to load and unload data from and to files, TaskList to contain and operate on
 * the lit of tasks, Ui to interact with the user, and Parser to decipher the user command.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor of Duke.
     * Attempts to load tasks from file, if fail start with empty list of tasks.
     *
     * @param filePath The path to the file to load tasks from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * A continuous loop of getting the user command, parsing to make sense of it and executing the command.
     * Stops when the user command is "bye.
     * @throws DukeException for any exceptions that occur during this run.
     */
    public void run() {
        ui.showHello();
        while (true) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                ui.showBye();
                break;
            }
            String commandType = parser.getCommandType(command);
            try {
                switch (commandType) {
                case "list":
                    ui.showList(tasks);
                    break;
                case "todo":
                    String description = parser.todoDescription(command);
                    ui.showAdded(tasks.addTodo(description), tasks.getLength());
                    break;
                case "deadline":
                    String[] descByWhen = parser.deadlineParams(command);
                    ui.showAdded(tasks.addDeadline(descByWhen[0], descByWhen[1]), tasks.getLength());
                    break;
                case "event":
                    String[] descAtWhen = parser.eventParams(command);
                    ui.showAdded(tasks.addEvent(descAtWhen[0], descAtWhen[1]), tasks.getLength());
                    break;
                case "done":
                    int doneNum = parser.markDoneNum(command);
                    ui.showMarkedDone(tasks.markDone(doneNum));
                    break;
                case "delete":
                    int deleteNum = parser.deleteNum(command);
                    ui.showDeleted(tasks.delete(deleteNum), tasks.getLength());
                    break;
                case "find":
                    String findWord = parser.findWord(command);
                    ui.showFound(tasks.find(findWord));
                    break;
                default:
                    throw new DukeUnknownInputException("Sorry but I do not recognise your command.");
                }
                storage.updateFile(tasks, tasks.getLength());
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:/Users/Min Suk/IdeaProjects/duke/data/tasks.txt").run();
    }
}