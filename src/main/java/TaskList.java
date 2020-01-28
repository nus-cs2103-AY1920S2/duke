import java.util.ArrayList;

/**
 * TaskList represents a list of tasks and stores Task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object. The tasks list will be empty.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object. Initializes the tasks list with the tasks in the specified list.
     * 
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the task in the specified position of the tasks list. 
     * 
     * @param taskNum Position of required task in the tasks list.
     * @return Required task.
     * @throws InvalidInstructionException If specified position is out of range in the tasks list.
     */
    public Task getTask(int taskNum) throws InvalidInstructionException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidInstructionException(String.format("Task #%d does not exist", taskNum));
        }

        return tasks.get(taskNum - 1);
    }

    /**
     * Returns the number of tasks in the tasks list.
     * 
     * @return Number of tasks in tasks list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns tasks list.
     * 
     * @return Tasks list.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Sets the task at the specified position in the tasks list as done.
     * 
     * @param taskNum Position of done task in the tasks list.
     * @throws InvalidInstructionException If specified position is out of range in the tasks list.
     */
    public void setAsDone(int taskNum) throws InvalidInstructionException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidInstructionException(String.format("Task #%d does not exist", taskNum));
        }

        this.tasks.get(taskNum - 1).markAsDone();
    }

    /**
     * Adds specified task to the tasks list.
     * 
     * @param task Task to be added to the tasks list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task at the specified position in the tasks list.
     * 
     * @param taskNum Position of task to be deleted in the tasks list.
     * @throws InvalidInstructionException If specified position is out of range in the tasks list.
     */
    public void deleteTask(int taskNum) throws InvalidInstructionException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidInstructionException(String.format("Task #%d does not exist", taskNum));
        }

        this.tasks.remove(taskNum - 1);
    }
}
