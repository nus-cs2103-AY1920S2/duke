package duke.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.taskList = new ArrayList<>(list);
    }

    public TaskList(TaskList other) {
        if (other == null) {
            this.taskList = new ArrayList<>();
        } else {
            this.taskList = new ArrayList<>(other.taskList);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        removeAtIndex(taskList.indexOf(task));
    }

    public void removeAllTask() {
        taskList.clear();
    }

    public Task removeAtIndex(int index) {
        return taskList.remove(index);
    }

    public void markAsDone(int index) {
        taskList.get(index).setIsDone(true);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isIndexValid(int index) {
        return index >= 0 && index < taskList.size();
    }

    public boolean isIndexValid(String index) {
        try {
            int idx = Integer.parseInt(index);
            return isIndexValid(idx);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns an array of all the names of tasks in the taskList.
     * <p>
     * Certain procedures (e.g., Printing of all tasks) requires access to the names of each
     * element in the taskList. This function returns all the names of tasks (from their respective
     * toString() functions) as an array.
     *
     * @return An array containing the names of every duke.task in the taskList.
     */
    public String[] getNames() {
        String[] names = new String[taskList.size()];
        for (int i = 0; i < names.length; ++i) {
            names[i] = taskList.get(i).toString();
        }
        return names;
    }
}
