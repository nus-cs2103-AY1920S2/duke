package commands;

import data.TaskManager;
import data.task.Task;
import ui.TextUi;

public abstract class Command {

    protected TaskManager taskManager;
    public String COMMAND_WORD;
    private int targetIndex = -1;

    //constructor
    public Command() {
    }

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return taskManager.getTaskList().getInternalList().get(getTargetIndex() - TextUi.DISPLAYED_INDEX_OFFSET);
    }

    /**
     * get target index
     * @return targetIndex
     */
    public int getTargetIndex() {
        return targetIndex;
    }

    public abstract CommandResult execute () ;

    public abstract CommandResult executeForGUI();
}
