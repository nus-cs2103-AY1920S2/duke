package duke.main;

import duke.exceptions.InvalidArgumentException;
import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    List<Task> taskList;
    Storage storage = null;

    public TaskList() {
        taskList = new ArrayList<>();
        storage = new Storage("data/duke.txt");
    }

    public TaskList(Storage storage) {
        taskList = new ArrayList<>();
        taskList.addAll(storage.getTasks());
        this.storage = storage;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int addTask(Task t) {
        taskList.add(t);
        return taskList.size() - 1;
    }

    public int size() {
        return taskList.size();
    }

    public Task markAsDone(String indexString) throws InvalidArgumentException {
        try {
            int index = Integer.parseInt(indexString);
            taskList.get(index - 1).markAsDone();
            return taskList.get(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new InvalidArgumentException();
        }
    }

    public Task deleteTask(String indexString) throws InvalidArgumentException {
        try {
            int index = Integer.parseInt(indexString);
            taskList.remove(index - 1);
            return taskList.get(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new InvalidArgumentException();
        }
    }

    public List<String> printTaskList() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            output.add((i + 1) + "." + taskList.get(i));
        }
        return output;
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public boolean clearTasks() {
        taskList.clear();
        return true;
    }

    public boolean saveTasks() {
        try {
            storage.save(taskList);
        } catch (Exception ex) {
            Ui.printException(ex);
        }
        return true;
    }
}
