package duchess.command;

import duchess.exception.DuchessException;
import duchess.storage.Storage;
import duchess.task.*;
import duchess.ui.Ui;
import duchess.util.DateTimeParser;

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
     * @return null.
     * @throws DuchessException If the list fails to be saved.
     */
    static Void handleTodoCommand(String command, TaskList taskList,
                                  Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        Task newTask = new ToDo(commands.get(1).trim());
        taskList.addTask(newTask);
        ui.printTaskAdded(newTask, taskList.size());
        storage.save(taskList);
        return null;
    }

    /**
     * Creates a {@code Event} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @return null.
     * @throws DuchessException If the list fails to be saved or /at [details] is missing.
     */
    static Void handleEventCommand(String command, TaskList taskList,
                                   Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/at")));
        if (details.size() < 2) {
            throw new DuchessException("I don't know when is your event! Please use /at [time here].");
        }
        Task newTask = new Event(details.get(0).trim(), details.get(1).trim());
        taskList.addTask(newTask);
        storage.save(taskList);
        ui.printTaskAdded(newTask, taskList.size());
        return null;
    }

    /**
     * Creates a {@code Deadline} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @return null.
     * @throws DuchessException If the list fails to be saved or /by is missing
     *                          or the deadline is of an unrecognizable format.
     */
    static Void handleDeadlineCommand(String command, TaskList taskList,
                                      Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split("/by")));
        if (details.size() < 2) {
            throw new DuchessException("I don't know when is your deadline! Please use /by [deadline here].");
        }
        String timeDetails = details.get(1).trim().toLowerCase();
        Task newTask = new Deadline(details.get(0).trim(), DateTimeParser.parseDateTime(timeDetails));
        taskList.addTask(newTask);
        storage.save(taskList);
        ui.printTaskAdded(newTask, taskList.size());
        return null;
    }

    /**
     * Prints out the given {@code TaskList} with the given {@code Ui} instance.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @return null.
     */
    static Void handleListCommand(String command, TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
        return null;
    }

    /**
     * Completes a {@code Task} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @return null.
     * @throws DuchessException If the list fails to be saved or the index is out
     *                          of bounds or the task is already completed.
     */
    static Void handleDoneCommand(String command, TaskList taskList,
                                  Ui ui, Storage storage) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        int indexAsInt = Integer.parseInt(commands.get(1).trim());
        if (indexAsInt < 0 || indexAsInt > taskList.size()) {
            throw new DuchessException("You're referring to a task that does not exist!");
        } else {
            Task taskCompleted = taskList.completeTask(indexAsInt - 1);
            ui.print("Oh? You actually completed something? Impressive...");
            storage.save(taskList);
            ui.printTask(taskCompleted);
        }
        return null;
    }

    /**
     * Deletes a {@code Task} based on the command and given the
     * entire command and the supporting instances.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @return null.
     * @throws DuchessException If the list fails to be saved or the index is out
     *                          of bounds.
     */
    static Void handleDeleteCommand(String command, TaskList taskList,
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
            ui.printTaskDeleted(taskToComplete, size);
        }
        return null;
    }

    /**
     * Prints the goodbye message with the given {@code Ui} instance.
     *
     * @param command  Full user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     * @return null.
     */
    static Void handleByeCommand(String command, TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbye();
        return null;
    }
}
