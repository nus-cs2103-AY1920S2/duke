package duke.command;

import duke.interaction.Ui;
import duke.task.TaskList;
import duke.util.Storage;

import java.util.Stack;

/**
 * Represents the Command for the "add" input by the user.
 * It adds a new task to the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class UndoCommand extends Command {

    private static Stack<Command> undoableCommandStack;

    /**
     * UndoCommand constructor.
     */
    public UndoCommand() {
        if (undoableCommandStack == null) {
            undoableCommandStack = new Stack<>();
        }
    }

    /**
     * Executes Add behaviour of adding a new task.
     *
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        if (!undoableCommandStack.empty()) {
            undoableCommandStack.pop().execute(taskList, storage);
        } else {
            Ui.showNothingToUndo();
        }
    }

    /**
     * Adds an undoable command to the undoable stack.
     *
     * @param command that can be executed.
     */
    public static void addUndoableCommand(Command command) {
        if (undoableCommandStack == null) {
            undoableCommandStack = new Stack<>();
        }
        undoableCommandStack.push(command);
    }

    /**
     * Inform if command is an exit command.
     *
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "undo - Reverts task list to prior to last command.";
    }
}
