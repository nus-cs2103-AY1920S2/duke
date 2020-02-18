import java.util.ArrayList;

/**
 * This class acts as a wrapper for an {@code ArrayList<Task>} which does all
 * the required manipulation of the tasks {@code Task} in the list.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a task into the {@code TaskList} while checking for duplicates.
     * 
     * @param task the {@code Task} to add
     * @throws DukeException if a duplicate {@code Task} is found in
     *                       {@code TaskList}
     */
    public void addTask(Task task) throws DukeException {
        for (Task t : taskList) {
            if (task.getClass() == t.getClass() && task.isSimilarTask(t)) {
                throw new DukeException("Sorry! This task already exists!");
            }
        }
        taskList.add(task);
    }

    public String printNumTasks() {
        return "Number of tasks: " + taskList.size();
    }

    /**
     * Returns and removes the task at the specified position {@code index}.
     * 
     * @param index the index of the task to be removed
     * @return the task that was removed
     * @throws DukeException if the specified index is more than or equal to the
     *                       size of the list
     */
    public Task removeTask(int index) throws DukeException {
        if (index >= taskList.size()) {
            throw new DukeException("Sorry! Task does not exist!");
        }
        return taskList.remove(index);
    }

    /**
     * Marks the task at the specified position {@code index} as done.
     * 
     * @param index the index of the task to be marked
     * @return the task that was marked
     * @throws DukeException if the specified index is more than or equal to the
     *                       size of the list or if the task has already been marked
     *                       as done
     */
    public Task markAsDone(int index) throws DukeException {
        if (index >= taskList.size()) {
            throw new DukeException("Sorry! Task does not exist!");
        }
        Task task = taskList.get(index);
        if (task.getIsDone()) {
            throw new DukeException("Sorry! Task is already marked as done.");
        }
        task.markAsDone();
        return task;
    }

    /**
     * Returns an {@code String} of the tasks currently in the list, where each
     * element (representing a task) is formatted to be human readable.
     * 
     * @return {@code String} of the tasks
     */
    public String listTasks() {
        if (taskList.isEmpty()) {
            return "Nothing in the list, good job! " + new String(Character.toChars(0x1F60A));
        }

        String lines = "Here are your tasks:\n";
        for (int i = 0; i < this.taskList.size(); i++) {
            lines += (i + 1) + ". " + this.taskList.get(i).getFullDescription() + "\n";
        }
        return lines.trim();
    }

    /**
     * Returns an {@code String} of the tasks with description matching that of the
     * input {@code description}, where each element (representing a task) is
     * formatted to be human readable.
     * 
     * @return {@code String} of the tasks
     */
    public String findTasks(String description) {
        if (taskList.isEmpty()) {
            return "Sorry, you don't seem to have any tasks yet.";
        }

        String lines = "";
        int index = 1;
        for (Task task : this.taskList) {
            if (task.getDescription().contains(description)) {
                lines += index + ". " + task.getFullDescription() + "\n";
                index++;
            }
        }

        if (lines.isEmpty()) {
            return "Sorry, nothing found.";
        }

        return "Here's what I found\n" + lines.trim();
    }
}