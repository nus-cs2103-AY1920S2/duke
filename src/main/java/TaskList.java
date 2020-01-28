import java.util.ArrayList;

/**
 * Class representation of TaskList
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * TaskList's default constructor
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * TaskList's constructor when there is data loaded from txt.file
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
