import java.util.LinkedList;

public class Tracker {
    private LinkedList<Task> list;
    private int totalTasks;

    /**
     * Creates a new Tracker object for tracking tasks in a list.
     */
    public Tracker() {
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
     * Deletes the given task from the tracking list.
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
    public Tracker find(String keyword) {
        Tracker matchingTasks = new Tracker();

        for (Task task : this.list) {
            String taskDescription = task.getContent();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

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
