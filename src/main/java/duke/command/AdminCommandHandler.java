package duke.command;

import duke.exception.DuchessException;
import duke.save.SaveState;
import duke.save.SaveStateStack;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.util.MagicStrings.ERROR_NO_MORE_UNDOS;
import static duke.util.StringCleaner.cleanAndLowerString;

/**
 * The {@code AdminCommandHandler} class contains all static methods to handle
 * administrative commands given the same arguments of command, taskList, ui and storage.
 */
public class AdminCommandHandler {
    /**
     * Prints the help message with the given {@code Ui} instance.
     *
     * @param command  Full raw user command string.
     * @param taskList List of tasks.
     * @param ui       Ui instance.
     * @param storage  Storage instance.
     */
    static String handleHelpCommand(String command, TaskList taskList, Ui ui,
                                    Storage storage, SaveStateStack saveStateStack) {
        assert Command.HELP.hasCommand(cleanAndLowerString(command)); // pre-condition
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
    static String handleByeCommand(String command, TaskList taskList, Ui ui,
                                   Storage storage, SaveStateStack saveStateStack) {
        assert Command.BYE.hasCommand(cleanAndLowerString(command)); // pre-condition
        return ui.printGoodbye();
    }

    static String handleUndoCommand(String command, TaskList taskList, Ui ui, Storage storage,
                                    SaveStateStack saveStateStack) throws DuchessException {
        assert Command.UNDO.hasCommand(cleanAndLowerString(command));
        SaveState lastSaveState = saveStateStack.pop();
        if (lastSaveState == null) {
            throw new DuchessException(ERROR_NO_MORE_UNDOS);
        }
        taskList.replaceTaskList(lastSaveState.getTasksFromSave());
        storage.save(taskList);
        return ui.printUndoMessage(lastSaveState.getLastCommand());
    }
}
