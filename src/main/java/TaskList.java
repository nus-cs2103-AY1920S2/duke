import java.util.ArrayList;

/**
 * A TaskList class that contains the task list
 * and has operations that allows it to modify the task list.
 */
public class TaskList {

    public ArrayList<Task> taskList = new ArrayList<>();

    /**
     * TaskList constructor that initializes an empty ArrayList of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * TaskList constructor that creates a TaskList based on an existing array list.
     * @param taskList ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null : "Tasklist cannot be null";
        this.taskList = taskList;
    }

    /**
     * Method that returns the size of the task list.
     * @return Integer that represented size of task list.
     */
    public int getSize() {
        assert taskList != null : "TaskList must exist";
        return taskList.size();
    }

    /**
     * Method that adds a task to the task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to add cannot be null";
        taskList.add(task);
    }

    /**
     * Method that removes a task at that index from the task list.
     * @param index Index which task to be removed from.
     */
    public void deleteTask(int index) throws DukeException {
        if (index > taskList.size()) {
            throw (new DukeException("There is no task number " + (index + 1) + "."));
        }
        taskList.remove(index);
    }

    /**
     * Method that sets the task at an index to be done.
     * @param index Index of task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is larger than the size of the ArrayList.
     */
    public void setDone(int index) throws IndexOutOfBoundsException {
        assert index < taskList.size() : "Index must be within range of tasklist.";
        taskList.get(index).markAsDone();
    }

    /**
     * Method that returns the task at the index.
     * @param index Index of task to be returned.
     * @return Task to be returned.
     */
    public Task getTask(int index) throws DukeException {
        if (index > taskList.size()) {
            throw (new DukeException("There is no task number " + (index + 1) + "."));
        }
        return taskList.get(index);
    }

    /**
     * Method that prints all tasks in the task list.
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        sb.append("\n");
        String output = "These items are in your list: \n";
        String tasks = sb.toString();
        output += tasks;

        return output;

    }

    /**
     * Method to return the ArrayList of tasks.
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Method to find all matching tasks in the task list.
     * @param str Keyword to find for all tasks.
     * @return
     */
    public TaskList find(String str) {
        TaskList matchedTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            String description = taskList.get(i).getDescription();
            if (description.contains(str)) {
                matchedTasks.getTaskList().add(taskList.get(i));
            }
        }
        return matchedTasks;
    }

}

