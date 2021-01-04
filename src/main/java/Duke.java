import java.io.IOException;

/**
 * Duke is the main class of the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean isGoodbye = false;

    static final String UNKNOWN_ERROR = "Unknown error has occurred.";

    /**
     * Constructs Duke object to use the chatbot function of Duke.
     * @param filePath The path where the data is saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            //Load from storage
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot program.
     * @param input User's input.
     * @return Duke's reply to user's input.
     * @throws IOException Writing data into storage.
     */
    public String run(String input) throws IOException {
        String fullCommand = input;
        String[] splitBySpace;
        int num;
        Task task;
        String desc;
        String by;

        if (fullCommand.equals("bye") || fullCommand.equals("goodbye")) {
            storage.writeData(tasks);
            isGoodbye = true;
            return ui.printGoodbye();
        }

        try {
            Command cmd = parser.parseCommand(fullCommand);
            desc = "";
            by = "";

            switch (cmd) {
            case LIST:
                if (tasks.getSize() == 0) {
                    return "List empty ok???";
                } else {
                    String listOfTasks = "";
                    for (int i = 0; i < tasks.getDukeList().size(); i++) {
                        listOfTasks = listOfTasks + (i + 1) + "." + tasks.getDukeList().get(i) + "\n";
                    }
                    return listOfTasks;
                }
            case DONE:
                num = parser.parseNum(fullCommand, tasks);
                assert num <= -3 : UNKNOWN_ERROR;

                tasks.markDone(num);
                return ui.printMarkDone(tasks.getSize(), num, tasks.getDukeList().get(num - 1).toString());
            case DELETE:
                num = parser.parseNum(fullCommand, tasks);
                assert num <= -3 : UNKNOWN_ERROR;

                Task taskToRemove = tasks.getDukeList().get(num - 1);
                tasks.removeTask(num);
                return ui.printTaskRemoved(tasks.getSize(), num, taskToRemove.toString());
            case TODO:
                String todoDesc = parser.parseDescription(fullCommand);

                task = new ToDo(todoDesc);
                tasks.addTask(task);
                return ui.printTaskAdded(tasks.getSize(), task.toString());
            case EVENT:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                by = parser.parseBy(fullCommand, Command.EVENT);

                task = new Event(desc, by);
                tasks.addTask(task);
                return ui.printTaskAdded(tasks.getSize(), task.toString());
            case DEADLINE:
                desc = parser.parseDescOfEventDeadline(fullCommand);
                by = parser.parseBy(fullCommand, Command.DEADLINE);

                task = new Deadline(desc, by);
                tasks.addTask(task);
                return ui.printTaskAdded(tasks.getSize(), task.toString());
            case FIND:
                String find = parser.parseDescription(fullCommand);
                String taskList = "";

                for (int i = 0; i < tasks.getSize(); i++) {
                    Task cur = tasks.getDukeList().get(i);

                    if (cur.getDescription().contains(find)) {
                        taskList = taskList + (i+1) + "." + tasks.getDukeList().get(i) + "\n";
                    }
                }

                return ui.printMatchingTask(taskList.trim(), find);
            case ENTERCOMMAND:
                throw new DukeException("Enter a command!!!!! Don't waste my time!");
            default:
                throw new DukeException("I can't understand what chu talking about. Try again?");
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Gets the Ui.
     * @return Ui.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Gets isGoodbye boolean type.
     * @return True if user typed "bye", or false if user did not type "bye".
     */
    public boolean getGoodbye() {
        return isGoodbye ? true : false;
    }
}

