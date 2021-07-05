package commands.add;

import commands.CommandResult;
import common.Messages;
import data.task.Task;

public class AddTodoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";
    public static final char COMMAND_TYPE = 'T';
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD + ": Add a Todo task to the DUKE system.";
    public static final String MESSAGE_USAGE_2 = "    Example: " + COMMAND_WORD + " read a book";

    private Task toDoTask;

    public AddTodoCommand(Task toDoTask) {
        this.toDoTask = toDoTask;
    }



    @Override
    public CommandResult execute() {
        taskManager.addTask(toDoTask);
        return new CommandResult(String.format(
                Messages.MESSAGE_TODO_SUCCESS,
                toDoTask.getTaskIndex()+Messages.LIST_INDEX_OFFSET,
                COMMAND_TYPE,
                toDoTask.getChar(),
                toDoTask.getTaskDescription()));
    }

    @Override
    public CommandResult executeForGUI() {
        taskManager.addTask(toDoTask);
        return new CommandResult(String.format(
                Messages.MESSAGE_TODO_SUCCESS,
                toDoTask.getTaskIndex()+Messages.LIST_INDEX_OFFSET,
                COMMAND_TYPE,
                toDoTask.getChar(),
                toDoTask.getTaskDescription()));
    }
}
