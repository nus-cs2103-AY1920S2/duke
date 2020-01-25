package dukebot;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public Storage storage;

    public TaskList(String storePath) {
        this.storage = new Storage(storePath);
        this.taskList = this.storage.loadFromFile();
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int taskInd) {
        return this.taskList.get(taskInd);
    }

    public boolean getIsDone(int taskInd) {
        return this.taskList.get(taskInd).getDone();
    }

    public void setDone(int taskInd) {
        this.taskList.get(taskInd).setDone();
        storage.saveToFile(this.taskList);
    }

    public Task addNewTask(String[] inpArr) throws InvalidTaskException{
        Task newTask = Task.makeTask(inpArr);
        this.taskList.add(newTask);
        storage.saveToFile(this.taskList);
        return newTask;
    }

    public Task deleteTask(int taskInd) {
        if (taskInd >= size() || taskInd < 0) {
            return null;
        } else {
            Task task = this.taskList.get(taskInd);
            this.taskList.remove(taskInd);
            storage.saveToFile(this.taskList);
            return task;
        }
    }

}