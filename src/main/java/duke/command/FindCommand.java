package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {
    private boolean isTimerOn;
    private String keywords;

    public FindCommand(String keywords, boolean isTimerOn) {
        this.keywords = keywords;
        this.isTimerOn = isTimerOn;
    }

    /**
     * Executes the 'find' command.
     *
     * @param tasks Task list
     * @param ui Current user interface
     * @param storage Current storage
     * @return String representation of a list consisting of tasks that contain the keyword(s)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskList().isEmpty()) {
            // List is empty
            return ui.showEmptyListMessage();
        } else {
            // List is NOT empty
            ArrayList<Task> taskList = tasks.getTaskList();
            ArrayList<Task> listOfMatchingTasks = new ArrayList<>();
            for (Task task : taskList) {
                if (task.toString().toLowerCase().contains(keywords.toLowerCase())) {
                    listOfMatchingTasks.add(task);
                }
            }
            if (isTimerOn) {
                return ui.showFindListTimerOn(listOfMatchingTasks);
            } else {
                return ui.showFindListTimerOff(listOfMatchingTasks);
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
}
