package commands.add;

import commands.CommandResult;
import common.Messages;
import data.task.DeadlineTask;
import data.task.Task;

public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";
    public static final char COMMAND_TYPE = 'D';
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD + ": Add a Deadline task to the DUKE system.";
    public static final String MESSAGE_USAGE_2 = "      Example: " + COMMAND_WORD+ " read a book";
    private final DeadlineTask deadlineTask;

    public AddDeadlineCommand(Task deadlineTask) {
        this.deadlineTask = (DeadlineTask) deadlineTask;
    }

    @Override
    public CommandResult execute() {
        if (deadlineTask == null) {
            return new CommandResult("Invalid Command Format");
        }
        taskManager.addTask(deadlineTask);
        //according to the data format
        return new CommandResult(String.format(
                Messages.MESSAGE_DEADLINE_SUCCESS+Messages.LIST_INDEX_OFFSET,
                COMMAND_TYPE,
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskStartTime()));
    }

    @Override
    public CommandResult executeForGUI() {
        if (deadlineTask == null) {
            return new CommandResult("Invalid Command Format");
        }
        taskManager.addTask(deadlineTask);
        //according to the data format
        return new CommandResult(String.format(
                Messages.MESSAGE_DEADLINE_SUCCESS+Messages.LIST_INDEX_OFFSET,
                COMMAND_TYPE,
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskStartTime()));
    }
}
