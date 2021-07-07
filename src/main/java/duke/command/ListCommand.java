package duke.command;

import duke.main.TaskList;
import duke.main.Ui;
import duke.main.Storage;

/**
 * Command that is executed when user inputs 'list'.
 */
public class ListCommand extends Command {
    private String listCommand;
    private boolean isTimerOn;

    public ListCommand(String listCommand, boolean isTimerOn) {
        this.listCommand = listCommand;
        this.isTimerOn = isTimerOn;
    }

    /**
     * Executes the 'list' command.
     *
     * @param tasks Task list
     * @param ui Current user interface
     * @param storage Current storage
     * @return String representation of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskList().isEmpty()) {
            // List is empty
            if (!listCommand.equals("")) {
                // Change display timer settings if necessary
                isTimerOn = listCommand.equals("/showtimer");
            }
            return ui.showEmptyListMessage();
        } else {
            // List is NOT empty
            if (listCommand.equals("")) {
                // No arguments given
                if (isTimerOn) {
                    return ui.showListTimerOn(tasks.getTaskList());
                } else {
                    return ui.showListTimerOff(tasks.getTaskList());
                }
            } else {
                // Arguments given
                if (listCommand.equals("/showtimer")) {
                    isTimerOn = true;
                    return ui.showListTimerOn(tasks.getTaskList());
                } else {
                    isTimerOn = false;
                    return ui.showListTimerOff(tasks.getTaskList());
                }
            }
        }
    }

    /**
     * Updates main function if it should exit the programme or not.
     *
     * @return True if 'bye' command is called, false otherwise. In this case, false is returned.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    public boolean isTimerOn() {
        return isTimerOn;
    }
}
