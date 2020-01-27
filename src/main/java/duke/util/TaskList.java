package duke.util;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Verifies whether the index
     * provided is within the range or not.
     * @param index the index of the task
     * @return the boolean value of whether the index given is
     * within the valid range.
     */

    public boolean isNotInRange(int index) {
        return index <= 0 || index > tasks.size() || tasks.size() <= 0;
    }

    /**
     * Marks the task of a particular index
     * to be done, and inform the client later. This method is
     * being called when a done command is entered by a client.
     * @param index The index of a particular task in the task list.
     */

    public String markDone(int index, Storage storage) {
        Task task = getTask(index);
        task.markAsDone();
        storage.rewriteTasksToFile(tasks);

        return "Nice! I've marked this task as done: \n" +
                String.format("   %s\n", task.toString()) +
                String.format("Now you have %d task(s) in the list.", tasks.size());
    }

    /**
     * Gets the task of a given index
     * from the list of tasks. The index value is normalized
     * by subtracting the value with 1 since the value starts
     * from 1.
     * @param index The index of the task in the list.
     * @return The Task instance of an index provided by the client.
     */

    private Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Prints all the tasks stored in the ArrayList
     * of the Duke instance.
     */

    public String listTasks() {
        if (tasks.size() == 0) {
            return "The list is currently empty. Fill me please!";
        }
        String message = "";
        for (int i = 1; i <= tasks.size(); i++) {
            message += String.format("%d. %s", i, tasks.get(i - 1));

            if (i != tasks.size()) {
                message += "\n";
            }
        }

        return message;
    }

    /**
     * Adds the task with the corresponding
     * type, and the description provided by the client.
     * @param task The task that wants to be added to the list.
     */

    public String addTask(Task task, Storage storage) {
        tasks.add(task);
        boolean isAppendMode = tasks.size() != 1;
        storage.writeTask(task, isAppendMode);
        return "Got it. I've added this task: \n" + String.format("    %s\n", task) +
                String.format("Now you have %d task(s) in the list.", tasks.size());
    }

    /**
     * Deletes the task of a particular index
     * from the list, then remove it from the file.
     * @param index The index of the task in the list to be deleted.
     */

    public String deleteTask(int index, Storage storage) {
        Task task = getTask(index);
        tasks.remove(index - 1);
        storage.rewriteTasksToFile(tasks);
        return "Noted. I've removed this task: \n " + String.format("    %s\n", task) +
                String.format("Now you have %d task(s) in the list.", tasks.size());
    }

    public int size() {
        return this.tasks.size();
    }
}
