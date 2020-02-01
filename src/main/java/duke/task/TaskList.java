package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public TaskList addTask(Task task) {
        this.taskList.add(task);
        return this;
    }

    public Task deleteTask(int taskNumber) {
        return this.taskList.remove(taskNumber - 1);
    }

    @Override
    public String toString() {
        String result = "";
        for (int currNum = 1; currNum <= taskList.size(); currNum++) {
            result = result + "\t" + currNum + ". " + taskList.get(currNum - 1) + "\n";
        }
        return result;
    }
}
