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
        this.taskList = taskList;
    }

    /**
     * Method that returns the size of the task list.
     * @return Integer that represented size of task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Method that adds a task to the task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Method that removes a task at that index from the task list.
     * @param index Index which task to be removed from.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Method that sets the task at an index to be done.
     * @param index Index of task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is larger than the size of the ArrayList.
     */
    public void setDone(int index) throws IndexOutOfBoundsException {
        taskList.get(index).markAsDone();
    }

    /**
     * Method that returns the task at the index.
     * @param index Index of task to be returned.
     * @return Task to be returned.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Method that prints all tasks in the task list.
     */
    public void printList() {
        System.out.println("These items are in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
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
    public ArrayList<Task> find(String str) {
        ArrayList<Task> matchedTasks = new ArrayList<Task>();
        for (int i = 0; i < taskList.size(); i++) {
            String description = taskList.get(i).getDescription();
            if (description.contains(str)) {
                matchedTasks.add(taskList.get(i));
            }
        }
        return matchedTasks;
    }

}

