package duke;

import exception.DukeException;
import java.util.ArrayList;
import task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** @return int number of tasks in tasklist */
    public int size() {
        return this.tasks.size();
    }

    /** @return Boolean whether task list is empty */
    public Boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /** @return ArrayList<String> returns an array of tasks in string format */
    public ArrayList<String> getAllTaskString() {
        ArrayList<String> taskString = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            taskString.add(String.format("%d.%s", i + 1, this.tasks.get(i)));
        }
        return taskString;
    }

    /**
     * @param searchTerm term that tasks should contain
     * @return ArrayList<String> arraylist of tasks that match the searchTerm in string format
     */
    public ArrayList<String> search(String searchTerm) {
        ArrayList<String> taskString = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            if (curr.contains(searchTerm)) {
                taskString.add(String.format("%d.%s", i + 1, this.tasks.get(i)));
            }
        }
        return taskString;
    }

    /**
     * @param index index of task
     * @return String string value of task at index
     */
    public String getTask(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * @param index index of task
     * @return Task removes and returns task
     */
    public Task popTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * @param input raw string of new task
     * @return Task returns created task
     * @throws DukeException exceptions when creating task
     */
    public Task addTask(String input) throws DukeException {
        Task newTask = Task.newTask(input);
        this.tasks.add(newTask);
        return newTask;
    }

    /** @param index index of task to set done */
    public void markDone(int index) {
        Task currTask = this.tasks.get(index);
        currTask.setDone();
    }

    /** @return ArrayList<Task> get arraylist of Tasks */
    public ArrayList<Task> getAllTask() {
        return this.tasks;
    }
}
