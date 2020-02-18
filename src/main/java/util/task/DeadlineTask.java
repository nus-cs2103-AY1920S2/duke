package util.task;

import util.Parser;

import java.util.Date;

/**
 * Represents a task with a deadline when it is due.
 * A deadline task has a description, a deadline date, and a true or false on whether it is completed.
 */
public class DeadlineTask extends Task {
    Date deadline;

    /**
     * Creates a util.task.DeadlineTask object.
     *
     * @param description The String description of the deadline task.
     * @param isDone      The boolean variable on whether the deadline task is completed.
     * @param deadline    The due Date of the deadline task.
     */
    private DeadlineTask(String description, boolean isDone, Date deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Static method to create a util.task.DeadlineTask object.
     * Throws an exception if the input text is not in the required format.
     *
     * @param description The String description of the deadline task.
     * @param deadline    The Date object of the date and time of the deadline.
     * @return A util.task.DeadlineTask.
     */
    public static DeadlineTask createDeadlineTask(String description, Date deadline) {
        assert description != null;
        assert deadline != null;

        return new DeadlineTask(description.trim(), false, deadline);
    }

    /**
     * Sets this util.task.DeadlineTask's complete status to true.
     * Overrides the abstract method in util.Task.
     *
     * @return A new complete util.task.DeadlineTask with the same description, deadline as the util.task.DeadlineTask instance.
     */
    @Override
    public DeadlineTask completeTask() {
        return new DeadlineTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[D] [" + super.getStatusIcon() + "] " + this.description
                + " (by: " + Parser.dateToString(this.deadline) + ")";
    }
}