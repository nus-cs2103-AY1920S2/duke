package duke.command;

import duke.exception.DuchessException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.DurationParser;
import duke.util.Pair;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Arrays;

import static duke.util.MagicStrings.ERROR_COMMAND_MISSING_INDEX;
import static duke.util.MagicStrings.ERROR_COMMAND_TOO_MANY_INDICES;
import static duke.util.MagicStrings.ERROR_INDEX_OUT_OF_BOUNDS;
import static duke.util.MagicStrings.ERROR_INVALID_SNOOZE_DURATION;
import static duke.util.MagicStrings.ERROR_SNOOZING_NON_DEADLINE;

/**
 * The {@code CommandHandler} class contains all static methods to handle
 * commands given the same arguments of command, taskList, ui and storage.
 */
public class TaskListCommandHandler {
    /**
     * Prints out the given {@code TaskList} with the given {@code Ui} instance.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     */
    static String handleListCommand(String command, TaskList taskList,
                                    Ui ui, Storage storage) throws DuchessException {
        assert command.trim().equalsIgnoreCase("list"); // pre-condition
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
    static String handleDoneCommand(String command, TaskList taskList,
                                    Ui ui, Storage storage) throws DuchessException {
        int index = getIntegerFromCommand(command);
        checkBoundsOfIndex(index, taskList);
        Task taskCompleted = taskList.completeTask(index - 1);
        storage.save(taskList);
        return ui.printTaskCompleted(taskCompleted);
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
        assert commands.get(0).equalsIgnoreCase("find"); // pre-condition
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
    static String handleDeleteCommand(String command, TaskList taskList,
                                      Ui ui, Storage storage) throws DuchessException {
        int index = getIntegerFromCommand(command);
        checkBoundsOfIndex(index, taskList);
        Task taskToDelete = taskList.getTask(index - 1);
        taskList.removeTask(index - 1);
        storage.save(taskList);
        return ui.printTaskDeleted(taskToDelete, taskList.size());
    }

    static String handleSnoozeCommand(String command, TaskList taskList,
                                      Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("/for", 2)));
        if (commands.size() < 2) {
            throw new DuchessException(ERROR_INVALID_SNOOZE_DURATION);
        }
        int index = getIntegerFromCommand(commands.get(0));
        checkBoundsOfIndex(index, taskList);
        Task taskToSnooze = taskList.getTask(index - 1);
        if (!(taskToSnooze instanceof Deadline)) {
            throw new DuchessException(ERROR_SNOOZING_NON_DEADLINE);
        }
        String duration = commands.get(1).trim().toLowerCase();
        TemporalAmount snoozePeriod = DurationParser.parseDuration(duration);
        ((Deadline) taskToSnooze).snooze(snoozePeriod);
        storage.save(taskList);
        return ui.printTaskSnoozed(taskToSnooze, DurationParser.parseDurationToString(duration));
    }

    private static Integer getIntegerFromCommand(String command) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        checkSizeOfCommands(commands);
        return Integer.parseInt(commands.get(1).trim());
    }

    private static void checkSizeOfCommands(ArrayList<String> commands) throws DuchessException {
        if (commands.size() < 2) {
            throw new DuchessException(ERROR_COMMAND_MISSING_INDEX);
        }
        if (commands.size() > 2) {
            throw new DuchessException(ERROR_COMMAND_TOO_MANY_INDICES);
        }
    }

    private static void checkBoundsOfIndex(int index, TaskList taskList) throws DuchessException {
        boolean isIndexTooLow = index < 0;
        boolean isIndexTooHigh = index >= taskList.size();
        if (isIndexTooLow || isIndexTooHigh) {
            throw new DuchessException(ERROR_INDEX_OUT_OF_BOUNDS);
        }
    }
}
