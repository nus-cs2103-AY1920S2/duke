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
     * Gets a Task.
     * @param index Specific index of the task in the list.
     * @return Returns a Task from the task list from the specified index.
     */
    public Task get(int index) {
        return arr.get(index);
    }

    /**
     * Gives the size of task list.
     * @return Returns the array size of the task list.
     */
    public int size() {
        return arr.size();
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
     * @param task Specific task in the list.
     */
    public void deleteTask(Task task) {
        arr.remove(task);
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
