import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor class. Creates an empty ArrayList of Tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Checks if TaskList is empty.
     * @return True if TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Checks the size of TaskList.
     * @return Size of TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retrieve the specified Task from TaskList.
     * @param index Index of Task to be retrieved.
     * @return Specified Task object.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Appends Task to TaskList.
     * @param task Task object to be appended.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the specified Task from TaskList.
     * @param index Index of Task to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Displays all the Tasks inside TaskList.
     * @return String object of each Task separated by a new line.
     */
    public String showList() {
        if (taskList.isEmpty()) {
            return "Your task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("");
            sb.append("Here is your list of tasks: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                sb.append("\n" + index + "." + task.toString());
            }
            return sb.toString();
        }
    }
}