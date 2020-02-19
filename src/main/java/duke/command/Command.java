package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.QuadFunction;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code Command} enum contains all of the different types of commands
 * recognised by {@code Duchess}.
 */
public enum Command {
    TODO(TaskCreationHandler::handleTodoCommand, "todo", "t"),
    EVENT(TaskCreationHandler::handleEventCommand, "event", "e"),
    DEADLINE(TaskCreationHandler::handleDeadlineCommand, "deadline", "dl"),
    LIST(TaskListCommandHandler::handleListCommand, "list", "l", "li"),
    DONE(TaskListCommandHandler::handleDoneCommand, "done", "d", "complete"),
    FIND(TaskListCommandHandler::handleFindCommand, "find", "f", "search"),
    DELETE(TaskListCommandHandler::handleDeleteCommand, "delete", "d", "del"),
    SNOOZE(TaskListCommandHandler::handleSnoozeCommand, "snooze"),
    SORT(TaskListCommandHandler::handleSortCommand, "sort", "s"),
    HELP(AdminCommandHandler::handleHelpCommand, "help", "h"),
    BYE(AdminCommandHandler::handleByeCommand, "bye", "exit", "quit");

    /**
     * Executes the command. Use {@code execute.apply} to run the function.
     */
    public final QuadFunction<String, TaskList, Ui, Storage> execute;

    /**
     * Contains all valid user inputs that maps to this type of command.
     */
    public final ArrayList<String> commands;

    /**
     * Initialises the Command enum type with the appropriate {@code execute}
     * function.
     *
     * @param execute The {@code QuadFunction} for the Command type.
     */
    Command(QuadFunction<String, TaskList, Ui, Storage> execute, String... commands) {
        this.execute = execute;
        this.commands = new ArrayList<>(Arrays.asList(commands));
    }

    public boolean hasCommand(String command) {
        return this.commands.contains(command);
    }
}
