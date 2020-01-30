import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor class. Creates an empty ArrayList of Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Checks if TaskList is empty.
     * @return True if TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks the size of TaskList.
     * @return Size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieve the specified Task from TaskList.
     * @param index Index of Task to be retrieved.
     * @return Specified Task object.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Appends Task to TaskList.
     * @param task Task object to be appended.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the specified Task from TaskList.
     * @param index Index of Task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Displays all the Tasks inside TaskList.
     * @return String object of each Task separated by a new line.
     */
    public String showList() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("");
            sb.append("Here is your list of tasks: ");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                int index = i + 1;
                sb.append("\n" + index + "." + task.toString());
            }
            return sb.toString();
        }
    }

    /**
     * Finds Tasks containing description that matches the keyword.
     * @param keyword Keyword to be matched.
     * @return A TaskList of matching Tasks.
     */
    public TaskList findTask(String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getDescription().contains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }
}