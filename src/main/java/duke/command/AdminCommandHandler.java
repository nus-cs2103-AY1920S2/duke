package duke.command;

import static duke.util.MagicStrings.ERROR_NO_MORE_UNDOS;
import static duke.util.MagicStrings.ERROR_STATS_INVALID_TIME;
import static duke.util.StringCleaner.cleanAndLowerString;

import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.DuchessException;
import duke.save.SaveState;
import duke.save.SaveStateStack;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The {@code AdminCommandHandler} class contains all static methods to handle
 * administrative commands given the same arguments of command, taskList, ui and storage.
 */
public class AdminCommandHandler {
    /**
     * Prints the help message with the given {@code Ui} instance.
     *
     * @param command        Full user command string.
     * @param taskList       List of tasks.
     * @param ui             Ui instance.
     * @param storage        Storage instance.
     * @param saveStateStack Collection of save states.
     * @return Help message.
     */
    static String handleHelpCommand(String command, TaskList taskList, Ui ui,
                                    Storage storage, SaveStateStack saveStateStack) {
        assert Command.HELP.hasCommand(cleanAndLowerString(command)); // pre-condition
        return ui.printHelpMessage();
    }

    /**
     * Prints the goodbye message with the given {@code Ui} instance.
     *
     * @param command        Full user command string.
     * @param taskList       List of tasks.
     * @param ui             Ui instance.
     * @param storage        Storage instance.
     * @param saveStateStack Collection of save states.
     * @return String containing the goodbye message.
     */
    static String handleByeCommand(String command, TaskList taskList, Ui ui,
                                   Storage storage, SaveStateStack saveStateStack) {
        assert Command.BYE.hasCommand(cleanAndLowerString(command)); // pre-condition
        return ui.printGoodbye();
    }

    /**
     * Undos the last action by the user.
     *
     * @param command        Full user command string.
     * @param taskList       List of tasks.
     * @param ui             Ui instance.
     * @param storage        Storage instance.
     * @param saveStateStack Collection of save states.
     * @return Success message of undo.
     * @throws DuchessException If no more undos are allowed.
     */
    static String handleUndoCommand(String command, TaskList taskList, Ui ui, Storage storage,
                                    SaveStateStack saveStateStack) throws DuchessException {
        assert Command.UNDO.hasCommand(cleanAndLowerString(command));
        SaveState lastSaveState = saveStateStack.pop();
        if (lastSaveState == null) {
            throw new DuchessException(ERROR_NO_MORE_UNDOS);
        }
        taskList.replaceTaskList(lastSaveState.getTasksFromSave());
        taskList.replaceArchive(lastSaveState.getArchiveFromSave());
        storage.save(taskList);
        return ui.printUndoMessage(lastSaveState.getLastCommand());
    }

    static String handleStatsCommand(String command, TaskList taskList, Ui ui, Storage storage,
                                     SaveStateStack saveStateStack) throws DuchessException {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(command.split("\\s", 2)));
        if (commands.size() < 2) {
            throw new DuchessException(ERROR_STATS_INVALID_TIME);
        }
        TemporalAmount statsPeriod;
        switch (cleanAndLowerString(commands.get(1))) {
        case "day":
            // Fallthrough
        case "today":
            statsPeriod = Period.ofDays(0);
            break;
        case "week":
            // Fallthrough
        case "this week":
            statsPeriod = Period.ofWeeks(1);
            break;
        case "month":
            // Fallthrough
        case "this month":
            statsPeriod = Period.ofMonths(1);
            break;
        case "year":
            // Fallthrough
        case "this year":
            statsPeriod = Period.ofYears(1);
            break;
        default:
            throw new DuchessException(ERROR_STATS_INVALID_TIME);
        }
        Integer[] stats = taskList.getStats(statsPeriod);
        return ui.printStats(stats, statsPeriod);
    }
}
