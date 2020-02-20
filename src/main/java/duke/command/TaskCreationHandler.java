package duke.command;

import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_DEADLINE_MISSING_DEADLINE;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_CONTENT;
import static duke.util.MagicStrings.ERROR_EVENT_MISSING_TIME_FRAME;
import static duke.util.MagicStrings.ERROR_INVALID_COMMAND;
import static duke.util.MagicStrings.ERROR_TODO_MISSING_CONTENT;
import static duke.util.StringCleaner.cleanAndLowerString;
import static duke.util.StringCleaner.cleanString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.DuchessException;
import duke.save.SaveStateStack;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.RecurringDeadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.util.DateTimeParser;
import duke.util.Frequency;
import duke.util.FrequencyParser;


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
     * @param command        Full raw user command string.
     * @param taskList       List of tasks.
     * @param ui             Ui instance.
     * @param storage        Storage instance.
     * @param saveStateStack Collection of save states.
     * @return Success message of todo creation.
     * @throws DuchessException If the list fails to be saved.
     */
    static String handleTodoCommand(String command, TaskList taskList, Ui ui, Storage storage,
                                    SaveStateStack saveStateStack) throws DuchessException {
        Task newTask = getTaskFromCommand(command);
        saveStateStack.saveState(command, taskList);
        return saveTask(newTask, taskList, storage, ui);
    }

    /**
     * Creates a {@code Event} based on the command and given the entire command and
     * the supporting instances.
     *
     * @param command        Full raw user command string.
     * @param taskList       List of tasks.
     * @param ui             Ui instance.
     * @param storage        Storage instance.
     * @param saveStateStack Collection of save states.
     * @return Success message of event creation.
     * @throws DuchessException If the list fails to be saved or /at [details] is
     *                          missing.
     */
    static String handleEventCommand(String command, TaskList taskList, Ui ui, Storage storage,
                                     SaveStateStack saveStateStack) throws DuchessException {
        Task newTask = getTaskFromCommand(command, "/at");
        saveStateStack.saveState(command, taskList);
        return saveTask(newTask, taskList, storage, ui);
    }

    /**
     * Creates a {@code Deadline} based on the command and given the entire command
     * and the supporting instances.
     *
     * @param command        Full raw user command string.
     * @param taskList       List of tasks.
     * @param ui             Ui instance.
     * @param storage        Storage instance.
     * @param saveStateStack Collection of save states.
     * @return Success message of deadline creation.
     * @throws DuchessException If the list fails to be saved or /by is missing or
     *                          the deadline is of an unrecognizable format.
     */
    static String handleDeadlineCommand(String command, TaskList taskList, Ui ui, Storage storage,
                                        SaveStateStack saveStateStack) throws DuchessException {
        Task newTask = getTaskFromCommand(command, "/by");
        saveStateStack.saveState(command, taskList);
        return saveTask(newTask, taskList, storage, ui);
    }

    // Private helper methods from this point onwards.

    /**
     * Returns a task based on the command given. Private helper method that
     * processes. the input and checks for any errors. If error is found, an
     * appropriate error is thrown.
     *
     * @param command Raw uncleaned command to process.
     * @return Task created from command.
     * @throws DuchessException If the given command is of an invalid format.
     */
    private static Task getTaskFromCommand(String command) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        String type = cleanAndLowerString(commands.get(0));
        if (commands.size() < 2) {
            handleMissingContent(type);
        }
        assert Command.TODO.commands.contains(type);
        return new ToDo(cleanString(commands.get(1)));
    }

    /**
     * Returns a task based on the command given. Private helper method that
     * processes. the input and checks for any errors. If error is found, an
     * appropriate error is thrown.
     *
     * @param command        Raw uncleaned command to process.
     * @param detailsKeyword Keyword to split command by.
     * @return Task created from command.
     * @throws DuchessException If the given command is of an invalid format.
     */
    private static Task getTaskFromCommand(String command, String detailsKeyword) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        String type = cleanAndLowerString(commands.get(0));
        if (commands.size() < 2) {
            handleMissingContent(type);
        }
        ArrayList<String> details = new ArrayList<>(Arrays.asList(commands.get(1).split(detailsKeyword)));
        if (details.size() < 2) {
            handleMissingDetails(type);
        }
        if (Command.EVENT.hasCommand(type)) {
            return new Event(cleanString(details.get(0)), cleanString(details.get(1)));
        }
        assert Command.DEADLINE.commands.contains(type);
        return getDeadlineFromDetails(commands.get(1));
    }

    /**
     * Returns a {@code Deadline} object if the user input suggests that
     * recurrence is desired, else return null.
     *
     * @param command Original user command.
     * @return {@code Deadline} object.
     * @throws DuchessException If the deadline format is invalid.
     */
    private static Deadline getDeadlineFromDetails(String command) throws DuchessException {
        ArrayList<String> details = new ArrayList<>(Arrays.asList(command.split("/")));
        Frequency frequency = null;
        LocalDateTime deadline = null;
        LocalDateTime recurrenceEndTime = null;
        for (int i = details.size() - 1; i > -1; i--) {
            String detail = details.get(i);
            String[] commands = detail.split("\\s", 2);
            switch (commands[0].toLowerCase()) {
            case "by":
                deadline = DateTimeParser.parseDateTime(cleanAndLowerString(commands[1]));
                break;
            case "every":
                frequency = FrequencyParser.parseFrequency(cleanAndLowerString(commands[1]));
                break;
            case "stop":
                recurrenceEndTime = DateTimeParser.parseDateTime(cleanAndLowerString(commands[1]));
                break;
            default:
                // Means a slash was inside the command. Assume it to be part of the previous segment,
                // thus we will append it to the previous segment.
                if (i != 0) {
                    details.set(i - 1, details.get(i - 1) + "/" + detail);
                }
                break;
            }
        }
        if (deadline == null) {
            throw new DuchessException(ERROR_DEADLINE_MISSING_DEADLINE);
        }
        String description = cleanString(details.get(0));
        if (frequency == null) {
            return new Deadline(description, deadline);
        }
        if (recurrenceEndTime == null) {
            return new RecurringDeadline(description, deadline, frequency);
        }
        return new RecurringDeadline(description, deadline, frequency, recurrenceEndTime);
    }

    /**
     * Throws the appropriate missing content error.
     *
     * @param type Cleaned {@code String} indicating type of task with missing content.
     * @throws DuchessException An exception with an appropriate error message.
     */
    private static void handleMissingContent(String type) throws DuchessException {
        if (Command.TODO.commands.contains(type)) {
            throw new DuchessException(ERROR_TODO_MISSING_CONTENT);
        } else if (Command.EVENT.commands.contains(type)) {
            throw new DuchessException(ERROR_EVENT_MISSING_CONTENT);
        } else if (Command.DEADLINE.commands.contains(type)) {
            throw new DuchessException(ERROR_DEADLINE_MISSING_CONTENT);
        } else {
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
        if (Command.EVENT.commands.contains(type)) {
            throw new DuchessException(ERROR_EVENT_MISSING_TIME_FRAME);
        }
        assert Command.DEADLINE.commands.contains(type);
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
