package duke.task;

import duke.common.Saveable;

import java.util.ArrayList;

public class TaskList implements Saveable {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new task to the list.
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Get an task from the list by index.
     * @param index The index of the task in the list.
     * @return The task specified by the index.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Obtain the number of tasks in the list.
     * @return The length of the list.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Mark an task in the list as done.
     * @param index The index of the task in the list.
     */
    public void markDone(int index) {
        tasks.get(index - 1).markDone();
    }

    /**
     * Delete an task in the list.
     * @param index The index of the task in the list.
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Formats the task list to be saved.
     * @return The tasks in save format.
     */
    public String toSaveFormat() {
        String output = "";
        for (Task task : tasks) {
            output += task.toSaveFormat() + "\n";
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "----------\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        output += "----------";
        return output;
    }
}