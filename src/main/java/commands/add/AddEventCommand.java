package commands.add;

import commands.CommandResult;
import common.Messages;
import data.task.EventTask;
import data.task.Task;

public class AddEventCommand extends AddCommand {

    public static final String COMMAND_WORD = "event";
    public static final char COMMAND_TYPE = 'E';
    public static final String MESSAGE_USAGE_1 = COMMAND_WORD + ": Add a Event task to the DUKE system.";
    public static final String MESSAGE_USAGE_2 = "    Example: " + COMMAND_WORD+ " read a book";


    private final EventTask eventTask;

    public AddEventCommand(Task eventTask) {
        this.eventTask = (EventTask) eventTask;
    }

    @Override
    public CommandResult execute() {
        if (eventTask == null) {
            return new CommandResult("Invalid Command Format");
        }
        taskManager.addTask(eventTask);
        return new CommandResult(String.format(
                Messages.MESSAGE_EVENT_SUCCESS+Messages.LIST_INDEX_OFFSET,
                COMMAND_TYPE,
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime()));
    }

    @Override
    public CommandResult executeForGUI() {
        if (eventTask == null) {
            return new CommandResult("Invalid Command Format");
        }
        taskManager.addTask(eventTask);
        return new CommandResult(String.format(
                Messages.MESSAGE_EVENT_SUCCESS+Messages.LIST_INDEX_OFFSET,
                COMMAND_TYPE,
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime()));
    }
}
