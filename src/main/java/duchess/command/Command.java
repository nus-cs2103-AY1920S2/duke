package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.ui.Ui;
import duchess.util.QuadFunction;

/**
 * The {@code Command} enum contains all of the different types of
 * commands recognised by {@code Duchess}.
 */
public enum Command {
    TODO(CommandHandler::handleTodoCommand),
    EVENT(CommandHandler::handleEventCommand),
    DEADLINE(CommandHandler::handleDeadlineCommand),
    LIST(CommandHandler::handleListCommand),
    DONE(CommandHandler::handleDoneCommand),
    FIND(CommandHandler::handleFindCommand),
    DELETE(CommandHandler::handleDeleteCommand),
    HELP(CommandHandler::handleHelpCommand),
    BYE(CommandHandler::handleByeCommand);

    /**
     * Executes the command. Use {@code execute.apply} to run the function.
     */
    public final QuadFunction<String, TaskList, Ui, Storage, Void> execute;

    /**
     * Initialises the Command enum type with the appropriate {@code execute}
     * function.
     *
     * @param execute The {@code QuadFunction} for the Command type.
     */
    Command(QuadFunction<String, TaskList, Ui, Storage, Void> execute) {
        this.execute = execute;
    }
}
