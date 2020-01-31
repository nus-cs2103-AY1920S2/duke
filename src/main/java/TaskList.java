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
     * @return Task task identified.
     */
    public Task getTask(int index) {
        Task task = null;
        try {
            task = this.lst.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("    Task index out of range.");
        }
        return task;
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.lst.add(task);
    }

    /**
     * Sets a task to be done based on its index, and returns boolean to indicate if the function ran without errors.
     *
     * @param index index of task stored in TaskList.
     * @return boolean to indicate if function was run successfully.
     */
    public boolean doneTask(int index) {
        boolean isDone = false;
        try {
            Task currTask = lst.get(index);
            currTask.setDone(true);
            isDone = true;
        } catch (NumberFormatException e) {
            System.err.println("    Please input an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("    Please try again, your number is out of range.");
        }
        return isDone;
    }

    /**
     * Delete a task based on its index, and returns boolean to indicate if the function ran without errors.
     *
     * @param index index of task stored in TaskList.
     * @return boolean to indicate if function was run successfully.
     */
    public boolean deleteTask(int index) {
        boolean isDone = false;
        try {
            Task currTask = lst.get(index);
            lst.remove(index);
            isDone = true;
        } catch (NumberFormatException e) {
            System.err.println("    Please input an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("    Please try again, your number is out of range.");
        }
        return isDone;
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
