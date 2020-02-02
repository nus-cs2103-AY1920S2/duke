import java.util.ArrayList;

/**
 * Contains the task list, as well as operations to amend existing tasks in thelist such as add/delete.
 */
public class TaskList {
    public ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Marks the task to be done.
     * @param option Specific index of task in the list.
     */
    public void markAsDone(int option) {
        arr.get(option - 1).setDone();
    }

    /**
     * Removes the task from the list.
     * @param option Specific index of task in the list.
     */
    public void deleteTask(int option) {
        arr.remove(option - 1);
    }

    /**
     * Adds the task to the list.
     * @param task Specifies task to be added.
     */
    public void addTask(Task task) {
        arr.add(task);
    }

    /**
     * Filters and returns a list of tasks pertaining to the keyword given.
     * @param keyword To find the specific task(s).
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).task.contains(keyword)) {
                tasksFound.add(arr.get(i));
            }
        }
        return tasksFound;
    }
}
