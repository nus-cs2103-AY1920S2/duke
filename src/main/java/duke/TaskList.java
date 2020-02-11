package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the given task to the TaskList.
     * @param toAdd the task to be added
     */
    public void add(Task toAdd) {
        tasks.add(toAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println(toAdd);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks the task at doneIndex as done. doneIndex is 0-indexed.
     * @param doneIndex the 0-indexed index of the task to be marked as done.
     */
    public void done(int doneIndex) {
        tasks.get(doneIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(doneIndex));
    }

    /**
     * Deletes the task at deleteIndex as done. deleteIndex is 0-indexed.
     * @param deleteIndex the 0-indexed index of the task to be deleted.
     */
    public void delete(int deleteIndex) {
        // deleteIndex is 0-indexed
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.remove(deleteIndex));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints all the tasks in the task list in pretty format.
     */
    public void list() {
        // TODO extract this to some UI method or something
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Prints all the tasks whose descriptions contain the search phrase provided, in a pretty format.
     * @param searchPhrase string to be searched in the task's description.
     */
    public void findAndPrint(String searchPhrase) {
        // filter tasks on description
        List<Task> foundTasks = this.tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(searchPhrase.toLowerCase()))
                .collect(Collectors.toList());

        // print the tasks
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + ". " + foundTasks.get(i).toString());
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
