
import test.Task;
import java.util.ArrayList;

/**
 *  CS2103 Individual Project
 * @author Wei Cheng
 * TaskList contains all the current task in an ArrayList
 */
public class TaskList {
    public ArrayList<Task> taskStorage;
    /**
     * Constructor for the TaskList
     */
    public TaskList(ArrayList<Task> ts){
        taskStorage = ts;
    }
    /**
     * Constructor for the TaskList
     */
    public TaskList(){
        taskStorage = new ArrayList<>();
    }
    /**
     * add the task to the TaskList
     * @param task an instance of the Task Class
     * @return a new TaskList
     */
    public TaskList add(Task task){
        taskStorage.add(task);
        return this;
    }
    /**
     * @param numb index of the task
     * @return the task called by the user
     */
    public Task getTask(int numb){
        return taskStorage.get(numb);
    }

    /**
     * remove a particular task based on the index
     * @param numb index of the task
     * @return a new TaskList
     */
    public TaskList removeTask(int numb){
        taskStorage.remove(numb);
        return this;
    }
}
