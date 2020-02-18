import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks ArrayList for TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task to be added to TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    /**
     * Get number of tasks in TaskList.
     *
     * @return Number of tasks in TaskList.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Get specified task in TaskList.
     *
     * @param taskNumber Task number in TaskList .
     * @return Task specified.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Deletes specified task in TaskList.
     *
     * @param taskNumber Task number in TaskList.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Finds specified task in TaskList based on description.
     *
     * @param description Task description to find task in TaskList.
     */
    public String findTask(String description) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String buffer = description.toLowerCase();
        for (Task task : tasks) {
            String s = task.description.toLowerCase();
            if (s.contains(buffer)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() != 0) {
            Ui.showLine();
            System.out.println("Found your matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(i + 1 + ". " + foundTasks.get(i).toString());
                sb.append(i + 1 + ". " + foundTasks.get(i).toString() + "\n");
            }
            Ui.showLine();
            return ("Found your matching tasks in your list:\n" + sb.toString().strip());
        } else {
            Ui.showLine();
            System.out.println("There are no matching tasks in your list");
            Ui.showLine();
            return ("There are no matching tasks in your list");
        }

    }


}
