package duke.command;

import duke.exception.IncorrectCommandException;
import duke.task.TaskList;

/**
 * An interface for the commands of this application.
 */
public interface Command {
    /**
     * Returns true of this command is an ExitCommand.
     * Otherwise, returns false.
     *
     * @return True only if this command is an ExitCommand.
     */
    boolean isExitCommand();

    /**
     * Returns true of this command is an UndoCommand.
     * Otherwise, returns false.
     *
     * @return True only if this command is an UndoCommand.
     */
    boolean isUndoCommand();

    /**
     * Returns true of this command is can be undone.
     * Otherwise, returns false.
     *
     * @return True only if this command can be undone.
     */
    boolean canBeUndone();

    /**
     * Executes this command and returns the CommandResponse.
     *
     * @param taskList The TaskList that this command should operate on.
     * @return The CommandResponse after executing this command.
     * @throws IncorrectCommandException If an error occurs when executing the command.
     */
    CommandResponse execute(TaskList taskList) throws IncorrectCommandException;

    /**
     * Undo this command and returns the CommandResponse.
     * @param taskList The TaskList that this command should operate on.
     * @return The CommandResponse after undoing this command.
     */
    CommandResponse undo(TaskList taskList);
}
