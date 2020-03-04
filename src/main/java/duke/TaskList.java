package duke;

import duke.task.CannotSnoozeException;
import duke.task.Snoozable;
import duke.task.Task;

import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.ArrayList;

/**
 * An ordered list of tasks.
 */
class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list containing the tasks in the specified list.
     *
     * @param tasks the list of tasks
     */
    TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Appends the specified task to the end of this task list.
     *
     * @param task the task to be added to the task list
     * @return the task list with the specified task appended
     */
    TaskList add(Task task) {
        tasks.add(task);
        assert !tasks.isEmpty();
        return this;
    }

    /**
     * Marks the task with the specified task number as completed.
     *
     * @param taskNumber the task number of the task to be completed
     * @return the completed task
     */
    Task complete(int taskNumber) throws TaskNumberOutOfBoundsException {
        try {
            int taskIndex = taskNumber - 1;
            Task completedTask = tasks.get(taskIndex).complete();
            tasks.set(taskIndex, completedTask);
            assert tasks.get(taskIndex).isCompleted();
            return completedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
    }

    /**
     * Removes the task with the specified task number from this task list.
     *
     * @param taskNumber the task number of the task to be deleted
     * @return the task that was removed from the task list
     */
    Task delete(int taskNumber) throws TaskNumberOutOfBoundsException {
        try {
            int taskIndex = taskNumber - 1;
            Task deletedTask = tasks.remove(taskIndex);
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
    }

    /**
     * Snoozes the task with the specified task number by the specified duration.
     *
     * @param taskNumber the task number of the task to be snoozed
     * @param duration the duration to snooze the task for
     * @return the snoozed task
     */
    Task snooze(int taskNumber, TemporalAmount duration) throws TaskNumberOutOfBoundsException, CannotSnoozeException {
        try {
            int taskIndex = taskNumber - 1;
            Task snoozedTask = (Task) ((Snoozable) tasks.get(taskIndex)).snooze(duration);
            tasks.set(taskIndex, snoozedTask);
            return snoozedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        } catch (ClassCastException e) {
            throw new CannotSnoozeException("Oops! This task cannot be snoozed.");
        }
    }

    /** Returns the first duplicate of the specified task in this task list, if any.
     *
     * @param task the specified task
     * @return the first duplicate of the specified task in this task list, if any
     */
    Task findDuplicate(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Returns a task list with the tasks whose descriptions contain the specified search term.
     *
     * @param searchTerm the search term
     * @return a task list containing tasks that match the search term
     */
    TaskList find(String searchTerm) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Removes all tasks from the task list.
     *
     * @return the empty task list
     */
    TaskList clear() {
        tasks.clear();
        return this;
    }

    /** Returns a list containing the tasks in the task list.
     *
     * @return a list containing the tasks in the task list
     */
    List<Task> asList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns {@code true} if this task list contains no tasks, {@code false} otherwise.
     *
     * @return {@code true} if this task list contains no tasks, {@code false} otherwise
     */
    boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return the number of tasks in this task list
     */
    int size() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of this task list
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                list.append("\n");
            }
            list.append((i + 1) + ". " + tasks.get(i));
        }
        return list.toString();
    }
}
