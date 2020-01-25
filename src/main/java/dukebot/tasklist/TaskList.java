package dukebot.tasklist;

import dukebot.DukeException;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int taskInd) {
        return this.taskList.get(taskInd);
    }

    public Task addNewTask(String[] inpArr) throws DukeException {
        Task newTask = Task.makeTask(inpArr);
        this.taskList.add(newTask);
        return newTask;
    }

    public Task deleteTask(int taskInd) {
        if (taskInd >= size() || taskInd < 0) {
            return null;
        } else {
            Task task = this.taskList.get(taskInd);
            this.taskList.remove(taskInd);
            return task;
        }
    }

}