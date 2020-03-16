import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList keeps track of the different tasks created by the user's input.
 */
class TaskList {
    protected List<Task> taskList;

    /**
     * The constructor for TaskList. TaskList is a wrapper class for a List containing different Tasks.
     *
     * @param taskList A List of Tasks.
     */
    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * The constructor for an empty TaskList; which initialises the List of Tasks to be empty.
     */
    TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the current TaskList.
     *
     * @return A List of Tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }
}
