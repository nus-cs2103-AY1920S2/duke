package commands;

import common.Messages;
import data.task.TaskList;
import ui.TextUi;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD
            + ": Find all tasks matches the given description.";
    public static final String MESSAGE_USAGE_2 ="    Example: " + COMMAND_WORD + " read a book.";
    public static final String MESSAGE_EMPTY_LIST = "Can not find any task description " +
            "contains this keyword";

    protected String toSearch;
    protected TaskList qualifiedTasks;
    public static String taskListMessage;

    public FindCommand() {
        
    }

    public FindCommand(String commandDescription) {
        this.toSearch = commandDescription;
    }

    @Override
    public CommandResult execute() {
        System.out.println(Messages.DIVIDER);
        if (isQualifiedTasksEmpty()) return new CommandResult(MESSAGE_EMPTY_LIST);
        System.out.println(Messages.DIVIDER);
        qualifiedTasks.clear();
        return new CommandResult(taskListMessage);
    }

    private boolean isQualifiedTasksEmpty() {
        qualifiedTasks = taskManager.searchTask(toSearch);
        if (qualifiedTasks.getInternalList().isEmpty()){
            return true;
        } else {
            taskListMessage = TextUi.printAllTasks(qualifiedTasks);
        }
        return false;
    }

    @Override
    public CommandResult executeForGUI() {
        System.out.println(Messages.DIVIDER);
        if (isQualifiedTasksEmpty()) return new CommandResult(MESSAGE_EMPTY_LIST);
        System.out.println(Messages.DIVIDER);
        qualifiedTasks.clear();
        return new CommandResult(taskListMessage);
    }
}
