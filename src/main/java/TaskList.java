import java.util.List;
import java.util.ArrayList;

/**
 * Stores a list of tasks and methods to make changes to the list
 */
public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the tasks list
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the tasks list
     * @param newTask the task to be added
     */
    public void addToList(Task newTask) {
        tasks.add(newTask);
    }


    /**
     * Deletes a task
     * @param taskNumber the target task number to delete
     * @return the task that has been deleted from the list
     */
    public Task delete(int taskNumber) {
        int index = taskNumber - 1;
        Task removedTask = tasks.remove(index);
        return removedTask;
    }

    /**
     * Edits a task
     * @param taskNumber to be edited from the list
     * @param newName the new name of the task
     * @return the edited task
     */
    public Task edit(int taskNumber, String newName) {
        int index = taskNumber - 1;
        Task targetTask = tasks.get(index);
        Task editedTask = targetTask.setName(newName);
        tasks.set(index, editedTask);
        return editedTask;
    }

    /**
     * Mark a task as done by substituting the current task with a completed task in the list
     * @param taskNumber the target task number to delete
     * @return the Task that is marked as done
     */
    public Task markAsDone(int taskNumber) {
        // the index of a task in the list is one less than the taskNumber
        int index = taskNumber - 1;
        Task currentTask = tasks.get(index);
        Task completedTask = currentTask.complete();
        tasks.set(index, completedTask);
        return completedTask;
    }

    /**
     * Gets a String representation of all tasks in the list
     * @return a String representing all tasks in the list
     */
    public String getAllTasks() {
        String result = "As you wish! Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n";
            Task currentTask = tasks.get(i);
            int taskNumber = i + 1;
            result += taskNumber + ". " + currentTask;
        }
        return result;
    }

    public String findTasks(String keyword) {
        String result = "Certainly! Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.toString().contains(keyword)) {
                result += "\n";
                int taskNumber = i + 1;
                result += taskNumber + ". " + currentTask;
            }
        }
        return result;
    }

    public List<Task> getList() {
        return tasks;
    }
}
