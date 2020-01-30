import duke.task.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

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