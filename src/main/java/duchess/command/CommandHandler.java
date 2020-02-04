package duchess.command;

import duchess.exception.DuchessException;
import duchess.storage.Storage;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.TaskList;
import duchess.task.ToDo;
import duchess.ui.Ui;
import duchess.util.DateTimeParser;
import duchess.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code CommandHandler} class contains all static methods to handle
 * commands given the same arguments of command, taskList, ui and storage.
 */
public class CommandHandler {
    /**
     * Creates a {@code ToDo} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved.
     */
    static String handleTodoCommand(String command, TaskList taskList,
                                    Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            throw new DuchessException("Your todo content cannot be empty! Type help if you need help.");
        }
        Task newTask = new ToDo(commands.get(1).trim());
        taskList.addTask(newTask);
        storage.save(taskList);
        return ui.printTaskAdded(newTask, taskList.size());
    }

    /**
     * Creates a {@code Event} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or /at [details] is missing.
     */
    static String handleEventCommand(String command, TaskList taskList,
                                     Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            throw new DuchessException("Your event content cannot be empty! Type help if you need help.");
        }
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/at")));
        if (details.size() < 2) {
            throw new DuchessException("I don't know when is your event! Please use /at [time here].");
        }
        Task newTask = new Event(details.get(0).trim(), details.get(1).trim());
        taskList.addTask(newTask);
        storage.save(taskList);
        return ui.printTaskAdded(newTask, taskList.size());
    }

    /**
     * Creates a {@code Deadline} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or /by is missing
     *                          or the deadline is of an unrecognizable format.
     */
    static String handleDeadlineCommand(String command, TaskList taskList,
                                        Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            throw new DuchessException("Your deadline content cannot be empty! Type help if you need help.");
        }
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/by")));
        if (details.size() < 2) {
            throw new DuchessException("I don't know when is your deadline! Please use /by [deadline here].");
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
    static String handleListCommand(String command, TaskList taskList,
                                    Ui ui, Storage storage) throws DuchessException {
        return ui.printTaskList(taskList);
    }

    /**
     * Completes a {@code Task} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or the index is out
     *                          of bounds or the task is already completed.
     */
    static String handleDoneCommand(String command, TaskList taskList,
                                    Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        int indexAsInt = Integer.parseInt(commands.get(1).trim());
        if (indexAsInt < 0 || indexAsInt > taskList.size()) {
            throw new DuchessException("You're referring to a task that does not exist!");
        } else {
            Task taskCompleted = taskList.completeTask(indexAsInt - 1);
            storage.save(taskList);
            return ui.printTaskCompleted(taskCompleted);
        }
    }

    /**
     * Finds a list of {@code Task}s based on the command and given the
     * entire command and the supporting instances. The list of {@code Task}s
     * are then printed out with the given {@code Ui} instance.
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
     * Deletes a {@code Task} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @throws DuchessException If the list fails to be saved or the index is out
     *                          of bounds.
     */
    static String handleDeleteCommand(String command, TaskList taskList,
                                      Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        int indexAsInt = Integer.parseInt(commands.get(1).trim());
        if (indexAsInt < 0 || indexAsInt > taskList.size()) {
            throw new DuchessException("You're referring to a task that does not exist!");
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
