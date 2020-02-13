package duke.util;

import java.util.ArrayList;

/*
 * ArchiveList
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 07 Feb 2020
 *
 */

/**
 * ArchiveList class defines the behavior of archive
 * management.
 * @author Mario Lorenzo
 */

public class ArchiveList implements IList<Task> {
    ArrayList<Task> tasks;

    /**
     * Constructs an ArchiveList instance.
     * @param tasks The list of the archived tasks.
     */

    public ArchiveList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints all the tasks archived in the ArrayList
     * of the Duke instance.
     */

    public String listArchivedTasks() {
        if (tasks.size() == 0) {
            return "The archive is currently empty. Fill me please!";
        }
        String message = "Here's your archived tasks:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            message += String.format("%d. %s", i, tasks.get(i - 1));

            if (i != tasks.size()) {
                message += "\n";
            }
        }

        return message;
    }

    /**
     * Archives the task with the corresponding
     * type, and the description provided by the client.
     * @param task The task that wants to be archived.
     * @return The output message.
     */

    public String archiveTask(Task task, Storage storage) {
        tasks.add(task);
        boolean isAppendMode = tasks.size() != 1;
        storage.write(task, isAppendMode);
        return "Got it. I've archived this task: \n"
                + String.format("    %s\n", task)
                + String.format("Now you have %d archived task(s).", tasks.size());
    }

    /**
     * Deletes the task of a particular index
     * from the archive, then remove it from the file.
     * @param index The index of the task in the archive to be deleted.
     * @return The output message.
     */

    public String deleteArchivedTask(int index, Storage storage) {
        Task task = getTask(index);
        tasks.remove(index - 1);
        storage.rewriteToFile(tasks);
        return "Noted. I've removed this task: \n "
                + String.format("    %s\n", task)
                + String.format("Now you have %d task(s) in the list.", tasks.size());
    }

    /**
     * Gets the task of a given index
     * from the list of tasks. The index value is normalized
     * by subtracting the value with 1 since the value starts
     * from 1.
     * @param index The index of the task in the list.
     * @return The Task instance of an index provided by the client.
     */

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the size of the list.
     * @return The size of the list.
     */

    public int size() {
        return this.tasks.size();
    }
}
