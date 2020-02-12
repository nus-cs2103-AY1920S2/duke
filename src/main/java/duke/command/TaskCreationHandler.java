package duke.command;

import duke.exception.DuchessException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.util.DateTimeParser;

import java.util.ArrayList;
import java.util.Arrays;

import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_DEADLINE;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_TIME_FRAME;
import static duke.util.MagicStrings.ERROR_INVALID_COMMAND;
import static duke.util.MagicStrings.ERROR_TODO_MISSING_CONTENT;

/**
 * The {@code TaskCreationHandler} class contains all static methods
 * to handle task creation commands given the same arguments of
 * command, taskList, ui and storage.
 */
public class TaskCreationHandler {
    /**
     * Creates a {@code ToDo} based on the command and given the entire command and
     * the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved.
     */
    static String handleTodoCommand(String command, TaskList taskList,
                                    Ui ui, Storage storage) throws DuchessException {
        Task newTask = getTaskFromCommand(command);
        return saveTask(newTask, taskList, storage, ui);
    }

    /**
     * Creates a {@code Event} based on the command and given the entire command and
     * the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or /at [details] is
     *                          missing.
     */
    static String handleEventCommand(String command, TaskList taskList,
                                     Ui ui, Storage storage) throws DuchessException {
        Task newTask = getTaskFromCommand(command, "/at");
        return saveTask(newTask, taskList, storage, ui);
    }

    /**
     * Creates a {@code Deadline} based on the command and given the entire command
     * and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or /by is missing or
     *                          the deadline is of an unrecognizable format.
     */
    static String handleDeadlineCommand(String command, TaskList taskList,
                                        Ui ui, Storage storage) throws DuchessException {
        Task newTask = getTaskFromCommand(command, "/by");
        return saveTask(newTask, taskList, storage, ui);
    }

    // Private helper methods from this point onwards.

    /**
     * Returns a task based on the command given. Private helper method that
     * processes. the input and checks for any errors. If error is found, an
     * appropriate error is thrown.
     *
     * @param command Command to process.
     * @return Task created from command.
     * @throws DuchessException If the given command is of an invalid format.
     */
    private static Task getTaskFromCommand(String command) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            handleMissingContent(commands.get(0).toLowerCase());
        }
        assert commands.get(0).equals("todo");
        return new ToDo(commands.get(1).trim());
    }

    /**
     * Returns a task based on the command given. Private helper method that
     * processes. the input and checks for any errors. If error is found, an
     * appropriate error is thrown.
     *
     * @param command Command to process.
     * @return Task created from command.
     * @throws DuchessException If the given command is of an invalid format.
     */
    private static Task getTaskFromCommand(String command, String detailsKeyword) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            handleMissingContent(commands.get(0).toLowerCase());
        }
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split(detailsKeyword)));
        if (details.size() < 2) {
            handleMissingDetails(commands.get(0).toLowerCase());
        }
        if (commands.get(0).equalsIgnoreCase("event")) {
            return new Event(details.get(0).trim(), details.get(1).trim());
        }
        assert commands.get(0).equalsIgnoreCase("deadline");
        String timeDetails = details.get(1).trim().toLowerCase();
        return new Deadline(details.get(0).trim(), DateTimeParser.parseDateTime(timeDetails));
    }

    /**
     * Throws the appropriate missing content error.
     *
     * @param type Type of task with missing content.
     * @throws DuchessException An exception with an appropriate error message.
     */
    private static void handleMissingContent(String type) throws DuchessException {
        switch (type) {
        case "todo":
            throw new DuchessException(ERROR_TODO_MISSING_CONTENT);
        case "event":
            throw new DuchessException(ERROR_EVENT_MISSING_CONTENT);
        case "deadline":
            throw new DuchessException(ERROR_DEADLINE_MISSING_CONTENT);
        default:
            throw new DuchessException(ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Throws the appropriate missing details error.
     *
     * @param type Type of task with missing details.
     * @throws DuchessException An exception with an appropriate error message.
     */
    private static void handleMissingDetails(String type) throws DuchessException {
        if (type.equals("event")) {
            throw new DuchessException(ERROR_EVENT_MISSING_TIME_FRAME);
        }
        assert type.equals("deadline");
        throw new DuchessException(ERROR_DEADLINE_MISSING_DEADLINE);
    }

    /**
     * Saves the newly created {@code Task} and returns a success message.
     *
     * @param newTask  {@code Task} to be saved.
     * @param taskList {@code TaskList} to save to.
     * @param storage  {@code Storage} to write to the save file with.
     * @param ui       {@code Ui} to generate the success message.
     * @return A success message based on the new Task.
     * @throws DuchessException Failure to write to the save file.
     */
    private static String saveTask(Task newTask, TaskList taskList,
                                   Storage storage, Ui ui) throws DuchessException {
        taskList.addTask(newTask);
        storage.save(taskList);
        return ui.printTaskAdded(newTask, taskList.size());
    }
}
