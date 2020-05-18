import java.io.Serializable;
import java.util.ArrayList;

/** Custom list to store and manipulate tasks. */
public class TaskList implements Serializable {
    protected ArrayList<Task> lst;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    /**
     * Another constructor for TaskList using an existing ArrayList.
     *
     * @param lst ArrayList to be used.
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Gets current size of list.
     *
     * @return current size of list.
     */
    public int getSize() {
        return this.lst.size();
    }

    /**
     * Gets task from ArrayList based on index.
     *
     * @param index index of task in ArrayList.
     * @return task identified.
     */
    public Task getTask(int index) {
        return this.lst.get(index);
    }

    /**
     * Gets task from ArrayList based on user input index.
     *
     * @param indexString String index of task in ArrayList.
     * @return task identified.
     */
    public Task getTaskFromString(String indexString) {

        Task task = null;
        try {
            int index = Integer.valueOf(indexString) - 1;
            task = this.lst.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Task index out of range.");
        } catch (NumberFormatException e) {
            System.err.println("Please input an integer.");
        }
        return task;
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        assert (task != null) : "Task is null";
        if (task != null) {
            this.lst.add(task);
        }
    }

    /**
     * Sets a task to be done based on its index.
     *
     * @param indexString String index of task stored in TaskList.
     */
    public void doneTask(String indexString) {
        try {
            int index = Integer.valueOf(indexString) - 1;
            Task currTask = lst.get(index);
            currTask.setDone(true);
        } catch (NumberFormatException e) {
            System.err.println("Please input an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Please try again, your number is out of range.");
        }
    }

    /**
     * Delete a task based on its index.
     *
     * @param indexString String index of task stored in TaskList.
     */
    public void deleteTask(String indexString) {
        try {
            int index = Integer.valueOf(indexString) - 1;
            Task currTask = lst.get(index);
            lst.remove(index);
        } catch (NumberFormatException e) {
            System.err.println("Please input an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Please try again, your number is out of range.");
        }
    }

    /**
     * Returns Tasklist containing tasks with keywords specified.
     *
     * @param toFind keyword to find in tasks' descriptions.
     * @return TaskList with all tasks that contains toFind.
     */
    public TaskList findMatchingTasks(String toFind) {
        TaskList tempLst = new TaskList();
        for (Task task : lst) {
            if (task.getDesc().contains(toFind)) {
                tempLst.addTask(task);
            }
        }
        return tempLst;
    }
}
