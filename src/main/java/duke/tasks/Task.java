package duke.tasks;

import duke.parser.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task implements Comparable<Task> {

    protected String description;
    protected boolean isDone;
    protected LocalDate taskDate;
    protected LocalTime taskTime;

    /**
     * Creates a Task object
     * @param description Information about the task
     */
    public Task(String description, LocalDate taskDate, LocalTime taskTime) {
        this.description = description;
        this.isDone = false;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }

    /**
     * Returns a string representation of a tick if task is done, else a cross
     * @return A string representation of a tick if task is done, else a cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks this task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if this task is done
     * @return true if task is done, else false
     */
    public boolean isDone() { return isDone; }

    /**
     * Returns a string representation of this task's description
     * @return A a string representation of this task's description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    @Override
    public int compareTo(Task other) {

        if (this.taskDate.toString().equals(other.taskDate.toString())) {
            // Dates are the same
            if (this.taskTime == null || other.taskTime == null) {
                return -1;
            }
            return this.taskTime.compareTo(other.taskTime);
        } else {
            return this.taskDate.compareTo(other.taskDate);
        }
    }

    /**
     * Returns a string representation of the date of this task
     * @return A string representation of the date of this task
     */
    public String getDateString() {
        return Parser.outputDateFormatter.format(taskDate);
    }

    /**
     * Returns a string representation of the time of this task
     * @return A string representation of the time of this task
     */
    public String getTimeString() {
        return Parser.outputTimeFormatter.format(taskTime);
    }

    /**
     * Returns the date attribute of this task
     * @return The date attribute of this task
     */
    public LocalDate getDate() {
        return taskDate;
    }

    /**
     * Returns the time attribute of this task
     * @return The time attribute of this task
     */
    public LocalTime getTime() {
        return taskTime;
    }
}