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
     * @param filePath File path of the data (text) file.
     * @throws IOException If there is an IO error.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        parser = new Parser();

        storage = new Storage(filePath);
        taskList = storage.load();
    }

    /**
     * Adds a Task to the task-list and prints the added Task.
     *
     * @param t Task to be added to the list.
     * @param taskList Current collection of all Tasks so far.
     */
    private void addAndPrintTask(Task t, TaskList taskList) {
        taskList.addTask(t);

        ui.printAddedTask(t, taskList);
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
     * Provides the main-entry point to the application.
     *
     * @throws IOException If IO error occurs.
     */
    public void run() throws IOException {
        ui.printWelcomeMsg();

        Task t;
        int index;

        while (true) {
            try {
                t = null;
                Command command = parser.parse(ui.readCmd(), taskList);

                switch (command.getCommandType()) {
                case LIST_CMD:
                    ui.printTaskList(taskList);
                    break;
                case DONE_CMD:
                    index = extractTaskIndexFromCmdParam(command);

                    t = taskList.getTask(index);
                    taskList.markAsDone(index);
                    ui.printTaskMarkedDone(t);

                    storage.saveTasksToFile(taskList);
                    break;
                case DELETE_CMD:
                    index = extractTaskIndexFromCmdParam(command);

                    t = taskList.getTask(index);
                    taskList.deleteTask(index);

                    ui.printTaskDeleted(t, taskList);

                    storage.saveTasksToFile(taskList);
                    break;
                case TODO_CMD:
                    t = new Todo(command.getParams()[0]);
                    addAndPrintTask(t, taskList);

                    storage.saveTasksToFile(taskList);
                    break;
                case DEADLINE_CMD:
                    t = new Deadline(command.getParams()[0], LocalDate.parse(command.getParams()[1]));
                    addAndPrintTask(t, taskList);

                    storage.saveTasksToFile(taskList);
                    break;
                case EVENT_CMD:
                    t = new Event(command.getParams()[0], LocalDate.parse(command.getParams()[1]));
                    addAndPrintTask(t, taskList);

                    storage.saveTasksToFile(taskList);
                    break;
                case BYE_CMD:
                    ui.printByeMsg();
                    return;
                }
            } catch (DukeException e) {
                ui.printLine(e + Ui.LF);
            }
        }
    }

    /**
     * Provides the main-entry point to the application.
     *
     * @param args Command-Line arguments.
     */
    public static void main(String[] args) {
        try {
            new Duke(Storage.DUKE_TXT_FILE_PATH).run();
        } catch (IOException e) {
            ui.printLine("Sorry, an IO error has occurred:");
        }
    }
}
