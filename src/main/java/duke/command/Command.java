package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.QuadFunction;

/**
 * The {@code Command} enum contains all of the different types of commands
 * recognised by {@code Duchess}.
 */
public enum Command {
    TODO(TaskCreationHandler::handleTodoCommand),
    EVENT(TaskCreationHandler::handleEventCommand),
    DEADLINE(TaskCreationHandler::handleDeadlineCommand),
    LIST(TaskListCommandHandler::handleListCommand),
    DONE(TaskListCommandHandler::handleDoneCommand),
    FIND(TaskListCommandHandler::handleFindCommand),
    DELETE(TaskListCommandHandler::handleDeleteCommand),
    SNOOZE(TaskListCommandHandler::handleSnoozeCommand),
    HELP(AdminCommandHandler::handleHelpCommand),
    BYE(AdminCommandHandler::handleByeCommand);

    /**
     * Executes the command. Use {@code execute.apply} to run the function.
     */
    public final QuadFunction<String, TaskList, Ui, Storage> execute;

    /**
     * Initialises the Command enum type with the appropriate {@code execute}
     * function.
     *
     * @param execute The {@code QuadFunction} for the Command type.
     */
    Command(QuadFunction<String, TaskList, Ui, Storage> execute) {
        this.execute = execute;
    }
}
