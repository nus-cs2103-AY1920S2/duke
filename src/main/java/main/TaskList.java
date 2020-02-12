package main;

import exception.DuplicateMarkAelitaException;
import exception.EmptyInputAelitaException;
import exception.InvalidArgumentAelitaException;
import exception.InvalidListItemAelitaException;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a task into the list.
     *
     * @param task the new task.
     */
    public void add(Task task) {

        taskList.add(task);
    }

    /**
     * Complete a task at the specified index.
     *
     * @param index the index.
     * @throws InvalidListItemAelitaException if index is out of bound.
     * @throws DuplicateMarkAelitaException   if the task has already been completed.
     */
    public void complete(int index) throws InvalidListItemAelitaException, DuplicateMarkAelitaException {

        if (index >= taskList.size() || index < 0) {
            throw new InvalidListItemAelitaException();
        }

        taskList.get(index).markAsDone();
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index the index.
     * @return the requested task.
     */
    public Task get(int index) {

        return taskList.get(index);
    }

    /**
     * Remove a specified task.
     *
     * @param task the task to be removed from the list.
     * @return if the tasks has been successfully removed.
     * @throws InvalidArgumentAelitaException if task list is empty.
     */
    public boolean remove(Task task) throws InvalidArgumentAelitaException {

        boolean done = taskList.remove(task);

        if (done) {
            Task.setTotalTaskCount(Task.getTotalTaskCount() - 1);
        }

        return done;

    }

    /**
     * Remove task at the specified index.
     *
     * @param index the index.
     * @return the task removed.
     * @throws InvalidListItemAelitaException if index is out of bound.
     * @throws InvalidArgumentAelitaException if task list is empty.
     */
    public Task remove(int index) throws InvalidListItemAelitaException, InvalidArgumentAelitaException {

        if (index >= taskList.size() || index < 0) {
            throw new InvalidListItemAelitaException();
        }

        Task.setTotalTaskCount(Task.getTotalTaskCount() - 1);

        return taskList.remove(index);
    }

    /**
     * Gets the size of the main.TaskList.
     *
     * @return the size.
     */
    public int getSize() {

        return taskList.size();
    }

    /**
     * List all task associated with the supplied date.
     *
     * @param date the date of interest.
     * @return the task list containing all the task associated with the date.
     */
    public TaskList list(LocalDate date) {

        TaskList tmp = new TaskList();

        for (Task task : taskList) {

            if (task instanceof Deadline && ((Deadline) task).getDate().equals(date)) {
                tmp.add(task);

            } else if (task instanceof Event && ((Event) task).getDate().equals(date)) {
                tmp.add(task);

            }
        }
        return tmp;
    }

    /**
     * List task list.
     *
     * @param keyword the keyword to search for.
     * @return the task list containing tasks associated with the keyword
     */
    public TaskList list(String keyword) {

        TaskList tmp = new TaskList();

        for (Task task : taskList) {

            try {
                String[] words = Parser.parse(task.getDescription(), " ");

                for (String word : words) {

                    if (word.toLowerCase().equals(keyword)) {
                        tmp.add(task);
                    }
                }

            } catch (EmptyInputAelitaException e) {
                e.getStackTrace();
            }
        }
        return tmp;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            builder.append("    ");
            builder.append((i + 1));
            builder.append(".");
            builder.append(taskList.get(i));
            builder.append("\n");
        }

        return builder.toString();
    }

}
