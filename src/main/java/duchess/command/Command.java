package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.ui.Ui;
import duchess.util.QuadFunction;

public enum Command {
    TODO(CommandHandler::handleTodoCommand),
    EVENT(CommandHandler::handleEventCommand),
    DEADLINE(CommandHandler::handleDeadlineCommand),
    LIST(CommandHandler::handleListCommand),
    DONE(CommandHandler::handleDoneCommand),
    FIND(CommandHandler::handleFindCommand),
    DELETE(CommandHandler::handleDeleteCommand),
    BYE(CommandHandler::handleByeCommand);

    public final QuadFunction<String, TaskList, Ui, Storage, Void> execute;

    Command(QuadFunction<String, TaskList, Ui, Storage, Void> execute) {
        this.execute = execute;
    }
}
