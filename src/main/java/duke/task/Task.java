package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Task(i.e. Todo) and a TaskDate(Which represented a Task with Date element(s) i.e. Deadline and Event).
 * A Task object corresponds to a Task represented by a String description and a boolean isDone. TaskDate (abstract,
 * Deadline and Event extend from this class) and Todo classes extend from this class.
 */
public class Task implements Comparable<Task> {
    public String description;
    public boolean isDone;
    LocalDate date;
    LocalTime time;

    /**
     * Creates a Task.
     * @param description Description of task
     * @param isDone Boolean of whether the task is done
     * @param date Date assigned to task
     * @param time Time assigned to task
     */
    public Task(String description, boolean isDone, LocalDate date, LocalTime time) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a Task.
     * @param description Description of task
     * @param isDone Boolean of whether the task is done
     * @param date Date assigned to task
     */
    public Task(String description, boolean isDone, LocalDate date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
        this.time = null;
    }

    /**
     * Returns a String corresponding to the Task's status represented by the boolean isDone.
     *
     * @return "Y" if isDone is true and "X" if isDone is false
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return Y or X symbols
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description of Task
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    @Override
    public int compareTo(Task t) {
        if (this.date.isAfter(t.date)) {
            return 1;
        } else if (this.date.isBefore(t.date)) {
            return -1;
        } else if (this.time != null && t.time != null) {
            assert this.date.isEqual(t.date) : t.date;
            if (this.time.isAfter(t.time)) {
                return 1;
            } else if (this.time.isBefore(t.time)) {
                return -1;
            } else {
                assert this.time.equals(t.time) : t.time;
                return 0;
            }
        } else if (this.time == null) {
            return 1;
        } else {
            assert t.time == null : t.time;
            return -1;
        }
    }
}
