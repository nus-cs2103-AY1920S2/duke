public enum Command {
    TODO(CommandHandler::handleTodoCommand),
    EVENT(CommandHandler::handleEventCommand),
    DEADLINE(CommandHandler::handleDeadlineCommand),
    LIST(CommandHandler::handleListCommand),
    DONE(CommandHandler::handleDoneCommand),
    DELETE(CommandHandler::handleDeleteCommand),
    BYE(CommandHandler::handleByeCommand);

    public final QuadFunction<String, TaskList, Ui, Storage, Void> execute;

    private Command(QuadFunction<String, TaskList, Ui, Storage, Void> execute) {
        this.execute = execute;
    }
}
