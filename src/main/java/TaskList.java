import java.util.ArrayList;

/**
 * Class to represent the list of tasks that
 * are being tracked.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int size;

    /**
     * Constructor.
     * @param tasks tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = this.tasks.size();
    }

    /**
     * Function to add a task into the task list.
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        this.size++;
    }

    /**
     * Function to delete a task away.
     * @param taskNumber the number of the task (eg task no 3)
     * @return the Task being deleted.
     * @throws IndexOutOfBoundsException if the taskNumber is not within the bounds.
     */
    public Task deleteTask(int taskNumber, HistoryManager historyManager) throws IndexOutOfBoundsException {
        // see if the taskNumber is legit
        this.tasks.get(taskNumber - 1);
        if (taskNumber >= 0 || taskNumber < this.tasks.size()) {
            historyManager.addState(this);
            Task deletedTask = this.tasks.remove(taskNumber - 1); // need to minus 1 as 0-index based arraylist
            this.size--;
            return deletedTask;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Function to get the task based on taskNumber.
     * @param taskNumber index of the task.
     * @return the Task.
     * @throws IndexOutOfBoundsException if the taskNumber is out of bounds.
     */
    public Task getTask(int taskNumber) throws IndexOutOfBoundsException {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Function to get the number of tasks in the list.
     * @return int (size of the list).
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Function to get the arraylist of tasks.
     * @return ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Function to find tasks based on keyWord.
     * @param keyWord word to be used for search.
     * @return ArrayList of tasks that were found.
     */
    public ArrayList<Task> findTasks(String keyWord) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasks) {
            if ((t.getTaskName().toLowerCase()).contains(keyWord.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }
}
