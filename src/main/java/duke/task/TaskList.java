package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Create a list of tasks.
     *
     * @param tasks the task list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        for (Task task: tasks) {
            this.tasks.add(task);
        }
    }

    /**
     * Get the size of the task list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Get the task at a specific index in the task list.
     *
     * @return the task at a specific index in the task list
     */
    public Task get(int getIndex) {
        return tasks.get(getIndex);
    }

    /**
     * Delete the task at a specific index from the task list.
     *
     * @return the deleted task
     */
    public Task delete(int deleteIndex) {
        return tasks.remove(deleteIndex);
    }

    /**
     * Add the task to the task list.
     *
     * @param task the task to be added
     */
    public void add(Task task) throws DukeException {
        if (hasNoDuplicates(task)) {
            tasks.add(task);
        } else {
            throw new DukeException("OOPS!!! Duplicate task detected!");
        }
    }

    /**
     * Check whether task list has a duplicate of the given task.
     * @param task the given task
     * @return true if there is no duplicate, else false
     */
    public boolean hasNoDuplicates(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Try to make a new to-do task from the input.
     *
     * @param taskString the input which contains the to-do task information
     * @return task the new to-do task
     */
    public Todo makeNewTodoTask(String taskString) throws DukeException {
        String[] todoTokens = taskString.split(" ");

        // If there is a description
        if (todoTokens.length > 1) {
            return new Todo(taskString.replaceFirst("^todo ", ""));
        }

        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Try to make a new event task from the input.
     *
     * @param taskString the input which contains the event task information
     * @return task the new event task
     */
    public Event makeNewEventTask(String taskString) throws DukeException {
        String[] eventTokens = taskString.split(" /at ");

        // If there is a description and a time/date
        if (eventTokens.length > 1) {
            String dateOrTime = eventTokens[1];
            String[] dateOrTimeTokens = dateOrTime.split(" ");

            // If there is a date and time
            if (dateOrTimeTokens.length <= 1) {
                throw new DukeException("OOPS!!! Event tasks require a specific time and date.");
            }

            try {
                LocalDate date = LocalDate.parse(dateOrTimeTokens[0]);
                String description = eventTokens[0].replaceFirst("^event ", "");
                return new Event(description, date, dateOrTimeTokens[1]);
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Cannot parse date or time.");
            }
        }

        throw new DukeException("OOPS!!! Event tasks require a description, "
                + "and a specific time and date (e.g. 2019-12-12 2-4pm).");
    }

    /**
     * Try to make a new deadline task from the input.
     *
     * @param taskString the input which contains the deadline task information
     * @return task the new deadline task
     */
    public Deadline makeNewDeadlineTask(String taskString) throws DukeException {
        String[] deadlineTokens = taskString.split(" /by ");

        // If there is a description and a deadline
        if (deadlineTokens.length > 1) {
            String dateOrTime = deadlineTokens[1];
            String[] dateOrTimeTokens = dateOrTime.split(" ");

            // If there is a date and time
            if (dateOrTimeTokens.length <= 1) {
                throw new DukeException("OOPS!!! Deadline tasks require a specific time and date.");
            }

            try {
                LocalDate date = LocalDate.parse(dateOrTimeTokens[0]);
                LocalTime time = LocalTime.parse(dateOrTimeTokens[1], DateTimeFormatter.ofPattern("HHmm"));
                String description = deadlineTokens[0].replaceFirst("^deadline ", "");
                return new Deadline(description, date, time);
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Cannot parse date or time.");
            }
        }

        throw new DukeException("OOPS!!! Deadline tasks require a description, "
                + "and a specific time and date (e.g. 2019-12-12 1800).");
    }
}