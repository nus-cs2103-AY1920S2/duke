package task;
import java.util.ArrayList;
/**
 * Represents a task list. A <code>TaskList</code> object corresponds to a task list e.g., <code>ArrayList<Task>
 *     taskList</code>
 */
public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList(){
        tasks = new ArrayList<>();
    }
    /**
     * Returns the text representation of the tasks to be saved in the storage file.
     * @return The text representation of the tasks.
     */
    public String toStringDukeTasks() {
        String taskString = "";
        for (Task task : tasks) {
            taskString += task.toStringTasks();
        }
        return taskString;
    }
    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void add(Task task){
        tasks.add(task);
    }
    /**
     * Deletes a task in the task list.
     * @param taskNum The index of the task to be deleted.
     */
    public void delete(int taskNum){
        tasks.remove(taskNum);
    }
    /**
     * Returns the task required.
     * @param taskNum The index of the task that is required.
     * @return The task required.
     */
    public Task getTask(int taskNum){
        return tasks.get(taskNum);
    }
}
