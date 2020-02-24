import java.util.LinkedList;

/**
 * Class that contains the task list & operations that can be done to the list.
 */

public class TaskList {
    private LinkedList<Task> list;
    private int totalTasks;

    /**
     * Creates a new Tracker object for tracking tasks in a list.
     */
    public TaskList() {
        this.list = new LinkedList();
        this.totalTasks = 0;
    }

    /**
     * Adds the given task into list for tracking.
     * @param task Task to add into list.
     */
    public void add(Task task) {
        this.totalTasks++;
        this.list.add(task);
    }

    /**
     * Marks the given task as done.
     * @param index Index of task to be marked as done.
     */
    public void markDone(int index) {
        Task task = this.list.get(index);
        task.setDone();
    }

    /**
     * Deletes the given task from the list.
     * @param index Index of the task to be deleted.
     */
    public void delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        this.totalTasks--;
    }

    /**
     * Returns a tracker that tracks a list of tasks containing the provided keyword.
     * @param keyword Keyword to search for.
     * @return Tracker that tracks the tasks containing the keyword.
     */
    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (Task task : this.list) {
            String taskDescription = task.getContent();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Updates the given task in the list.
     * @param index Index of the task to be updated.
     * @param type The portion of the task to be updated.
     * @param content The new content of the portion to be updated.
     * @throws DukeException If the specified portion of task is not valid/can not be updated.
     */
    public void update(int index, String type, String content) throws DukeException {
        Task task = this.list.get(index);

        if (type.equals("description")) {
            task.updateContent(content);
        } else if (type.equals("date")) {
            task.updateDate(content);
        } else {
            throw new DukeException("OOPS!!! I can't edit that :-(");
        }
    }

    /**
     * Returns list of tasks being tracked.
     * @return List of tasks.
     */
    public LinkedList<Task> showList() {
        return this.list;
    }

    /**
     * Returns the total number of tasks in the list.
     * @return Total number of tasks to be tracked.
     */
    public int getTotalTasks() {
        return this.totalTasks;
    }
}
