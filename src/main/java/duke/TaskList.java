package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores and maintains the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        tasks = new ArrayList<>();
        size = 0;
    }

    /**
     * Formats the current tasks into a String to be saved into a txt file.
     * @return A String of all the current tasks
     */
    public String saveFormat() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Task t = tasks.get(i);
            output.append(t.writeToFile()).append("\n");
        }
        return output.toString();
    }

    public void addTask(Task t) {
        tasks.add(t);
        size++;
    }

    /**
     * Removes the Task at index from the List.
     * @param index int index of the Task to be removed.
     */
    public void remTask(int index) {
        assert index > 0 : "Invalid index is used.";
        tasks.remove(index);
        size--;
    }

    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return size;
    }
}
