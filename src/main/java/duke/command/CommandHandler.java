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
import duke.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_DEADLINE;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_TIME_FRAME;
import static duke.util.MagicStrings.ERROR_INDEX_OUT_OF_BOUNDS;
import static duke.util.MagicStrings.ERROR_TODO_MISSING_CONTENT;

/**
 * The {@code CommandHandler} class contains all static methods to handle
 * commands given the same arguments of command, taskList, ui and storage.
 */
public class CommandHandler {
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
    static String handleTodoCommand(String command, TaskList taskList, Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            throw new DuchessException(ERROR_TODO_MISSING_CONTENT);
        }
        Task newTask = new ToDo(commands.get(1).trim());
        taskList.addTask(newTask);
        storage.save(taskList);
        return ui.printTaskAdded(newTask, taskList.size());
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
    static String handleEventCommand(String command, TaskList taskList, Ui ui, Storage storage)
            throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            throw new DuchessException(ERROR_EVENT_MISSING_CONTENT);
        }
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/at")));
        if (details.size() < 2) {
            throw new DuchessException(ERROR_EVENT_MISSING_TIME_FRAME);
        }
        Task newTask = new Event(details.get(0).trim(), details.get(1).trim());
        taskList.addTask(newTask);
        storage.save(taskList);
        return ui.printTaskAdded(newTask, taskList.size());
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
    static String handleDeadlineCommand(String command, TaskList taskList, Ui ui, Storage storage)
            throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            throw new DuchessException(ERROR_DEADLINE_MISSING_CONTENT);
        }
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/by")));
        if (details.size() < 2) {
            throw new DuchessException(ERROR_DEADLINE_MISSING_DEADLINE);
        }
        String timeDetails = details.get(1).trim().toLowerCase();
        Task newTask = new Deadline(details.get(0).trim(), DateTimeParser.parseDateTime(timeDetails));
        taskList.addTask(newTask);
        storage.save(taskList);
        return ui.printTaskAdded(newTask, taskList.size());
    }

    /**
     * Prints out the given {@code TaskList} with the given {@code Ui} instance.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     */
    static String handleListCommand(String command, TaskList taskList, Ui ui, Storage storage) throws DuchessException {
        return ui.printTaskList(taskList);
    }

    /**
     * Completes a {@code Task} based on the command and given the entire command
     * and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or the index is out of
     *                          bounds or the task is already completed.
     */
    static String handleDoneCommand(String command, TaskList taskList, Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        int indexAsInt = Integer.parseInt(commands.get(1).trim());
        if (indexAsInt < 0 || indexAsInt > taskList.size()) {
            throw new DuchessException(ERROR_INDEX_OUT_OF_BOUNDS);
        } else {
            Task taskCompleted = taskList.completeTask(indexAsInt - 1);
            storage.save(taskList);
            return ui.printTaskCompleted(taskCompleted);
        }
    }

    /**
     * Finds a list of {@code Task}s based on the command and given the entire
     * command and the supporting instances. The list of {@code Task}s are then
     * printed out with the given {@code Ui} instance.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     */
    static String handleFindCommand(String command, TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        ArrayList<Pair<Task, Integer>> filteredTaskList = taskList.find(commands.get(1).trim());
        return ui.printFilteredTaskList(filteredTaskList);
    }

    /**
     * Deletes a {@code Task} based on the command and given the entire command and
     * the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or the index is out of
     *                          bounds.
     */
    static String handleDeleteCommand(String command, TaskList taskList, Ui ui, Storage storage)
            throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        int indexAsInt = Integer.parseInt(commands.get(1).trim());
        if (indexAsInt < 0 || indexAsInt > taskList.size()) {
            throw new DuchessException(ERROR_INDEX_OUT_OF_BOUNDS);
        } else {
            Task taskToComplete = taskList.getTask(indexAsInt - 1);
            taskList.removeTask(indexAsInt - 1);
            int size = taskList.size();
            storage.save(taskList);
            return ui.printTaskDeleted(taskToComplete, size);
        }
    }

    /**
     * Prints the help message with the given {@code Ui} instance.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     */
    static String handleHelpCommand(String command, TaskList taskList, Ui ui, Storage storage) {
        return ui.printHelpMessage();
    }

    /**
     * Prints the goodbye message with the given {@code Ui} instance.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     */
    static String handleByeCommand(String command, TaskList taskList, Ui ui, Storage storage) {
        return ui.printGoodbye();
    }
}
