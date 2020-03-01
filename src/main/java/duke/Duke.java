package duke;

import java.time.LocalDate;
import java.io.IOException;

/**
 * Embeds the main logic of the program and acts as the program driver, invoking relevant classes.
 */
public class Duke {
    private static Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructs a new Duke object.
     *
     */
    public Duke() {
        try {
            ui = new Ui();
            parser = new Parser();

            storage = new Storage(Storage.DUKE_TXT_FILE_PATH);
            taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a Task to the task-list and prints the added Task.
     *
     * @param t Task to be added to the list.
     * @param taskList Current collection of all Tasks so far.
     * @return The task that was added to list.
     * @throws IOException If IO error occurred.
     */
    private String addPrintStoreTask(Task t, TaskList taskList) throws IOException {
        taskList.addTask(t);

        storage.saveTasksToFile(taskList);

        return ui.printAddedTask(t, taskList);
    }

    /**
     * Extracts the Task index, as per the task-list, from the Command object.
     *
     * @param command Command object representing command enetered by user.
     * @return Task index, as per the task-list.
     */
    private int extractTaskIndexFromCmdParam(Command command) {
        return (Integer.parseInt(command.getParams()[0]) - 1);
    }

    /**
     * Generates a response to user input from the GUI.
     *
     * @param input Raw input command entered by user.
     * @return Respond by the Duke chatbot application.
     */
    public String getResponse(String input) throws IOException {
        Task t;
        TaskList foundTaskList;
        int index;

        try {
            t = null;
            Command command = parser.parse(ui.readCmd(input), taskList);

            assert command != null;

            switch (command.getCommandType()) {
            case LIST_CMD:
                return ui.printTaskList(taskList);
            case DONE_CMD:
                index = extractTaskIndexFromCmdParam(command);

                t = taskList.getTask(index);
                taskList.markAsDone(index);
                storage.saveTasksToFile(taskList);
                return ui.printTaskMarkedDone(t);
            case DELETE_CMD:
                index = extractTaskIndexFromCmdParam(command);

                t = taskList.getTask(index);
                taskList.deleteTask(index);

                storage.saveTasksToFile(taskList);
                return ui.printTaskDeleted(t, taskList);
            case TODO_CMD:
                t = new Todo(command.getParams()[0]);

                return addPrintStoreTask(t, taskList);
            case DEADLINE_CMD:
                t = new Deadline(command.getParams()[0], LocalDate.parse(command.getParams()[1]));

                return addPrintStoreTask(t, taskList);
            case EVENT_CMD:
                t = new Event(command.getParams()[0], LocalDate.parse(command.getParams()[1]));

                return addPrintStoreTask(t, taskList);
            case FIND_CMD:
                foundTaskList = taskList.findByKeyword(command.getParams()[0]);

                return ui.printFoundTaskList(foundTaskList);
            case HELP_CMD:
                return ui.printHelpMsg();
            case BYE_CMD:
                return ui.printByeMsg();
            default:
                break;
            }
        } catch (DukeException e) {
            return ui.printLine(e + Ui.LF);
        }

        return "error";
    }

    /**
     * Generates a welcome response as the application starts up, from the GUI.
     *
     * @return Welcome response by the Duke chatbot application.
     */
    public String getWelcomeResponse() {
        return ui.printWelcomeMsg();
    }
}
