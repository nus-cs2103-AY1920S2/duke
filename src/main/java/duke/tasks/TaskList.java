package duke.tasks;

import java.util.ArrayList;

import duke.tags.Tag;

/**
 * Represents the current list of tasks.
 *
 * @author Firzan Armani
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * TaskList constructor.
     * Used for importing from Storage (an input saved file).
     *
     * @param taskList An existing ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * TaskList constructor.
     * Creates an empty list if there are no tasks to import.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Getter to return the TaskList as an ArrayList of Tasks.
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Getter to return the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Getter to return the task at a particular index in the TaskList.
     *
     * @param index Index of the Task in the TaskList
     * @return The task at the specified index
     */
    public Task getTaskAt(int index) {
        return taskList.get(index);
    }

    /**
     * Setter to mark the task at the specified index, to done and returns the Task.
     *
     * @param index Index of the Task in the TaskList
     * @return The task set to done
     */
    public Task setDone(int index) {
        taskList.get(index).setTaskDone();
        return taskList.get(index);
    }

    /**
     * Returns a string of the number of tasks in the TaskList.
     *
     * @return A string statement stating the total number of tasks in the TaskList
     */
    public String printTasksTotal() {
        return "Now you have " + this.getSize() + " tasks in the list";
    }

    /**
     * Deletes the Task at the index specified.
     *
     * @param index Index of the Task in the TaskList
     * @return The Task deleted
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Adds the Task to the current TaskList.
     *
     * @param task The new Task to be added
     * @return True if the Task was added successfully
     */
    public boolean addTask(Task task) {
        assert task != null : "No task to be added";
        assert task.getTaskName() != null : "Task has a missing description";
        return taskList.add(task);
    }

    /**
     * Filter the tasks that contain the keyword as a substring.
     *
     * @param keyword String to find in the task names.
     * @return An ArrayList containing tasks that contain the keyword as a substring.
     */
    public ArrayList<Task> filterTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<Task>(this.taskList);
        filteredTasks.removeIf((task) -> !task.getTaskName().toLowerCase().contains(keyword.toLowerCase()));
        return filteredTasks;
    }

    public ArrayList<Task> getTaggedTasks(String tagName) {
        ArrayList<Task> filteredTasks = new ArrayList<Task>(this.taskList);
        filteredTasks.removeIf((task) -> !task.getTags().contains(new Tag(tagName)));
        return filteredTasks;
    }
}