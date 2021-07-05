package commands;

import common.Messages;
import ui.TextUi;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks in the DUKE system as a list with index numbers.\n"
            + "    Example: " + COMMAND_WORD;
    public static final String MESSAGE_EMPTY_LIST = "There is no current task in the list!";
    public static String taskListMessage;
    public ListCommand() {

    }

    @Override
    /**
     * If the list is not empty, return null. Do the display inside printAllTasks() function.
     * If the list is empty, display the empty list message by return a CommandResult object.
     */
    public CommandResult execute() {
        taskManager.updateTaskIndex();
        if (taskManager.getTaskList().getInternalList().size()>0){
            taskListMessage = TextUi.printAllTasks(taskManager.getTaskList());
            return new CommandResult((taskListMessage));
        } else {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }
    }

    @Override
    public CommandResult executeForGUI() {
        System.out.println(Messages.DIVIDER);
        if (taskManager.getTaskList().getInternalList().size()>0){
            taskListMessage = Messages.printAllTasks(taskManager.getTaskList());
            return new CommandResult((taskListMessage));
        } else {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }
    }

}
