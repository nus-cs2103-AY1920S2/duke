package sharadhr.duke.task;

import sharadhr.duke.exception.DukeEmptyDetailException;

import java.io.IOException;

/**
 * A Task class that allows creating a static list of tasks, that can be added
 * to, iterated through and polled for contents, or deleted from.
 * <p>
 * Also allows initialising an instance of a Task, which come with appropriate
 * instance methods.
 */
public abstract class Task {
    // A list of tasks.
    protected String detail;
    protected boolean isComplete;

    Task() {
        this.isComplete = false;
        assert !isComplete;
    }

    /**
     * Initialises a {@link Task} with some specified {@code detail}, and is set as
     * incomplete.
     *
     * @param name the task detail
     * @throws DukeEmptyDetailException if {@code detail} is blank, as specified by
     *                                  {@link String#isBlank()}.
     */
    protected Task(String detail) throws DukeEmptyDetailException {
        this();

        if (detail.isBlank()) {
            throw new DukeEmptyDetailException(this.getClass().getName());
        }

        this.detail = detail;
    }

    /**
     * @param detail
     * @param isComplete
     * @throws DukeEmptyDetailException
     */
    protected Task(String detail, boolean isComplete) throws DukeEmptyDetailException {
        this(detail);
        this.isComplete = isComplete;
    }

    /**
     * Returns a character representing the completion state of this task.
     *
     * @return {@code '✔'} if complete, {@code '✘'} otherwise
     */
    public char getCompleteIcon() {
        return isComplete ? '✔' : '✘';
    }

    /**
     * Marks this task as complete, and returns the state of the task (must be
     * {@code true}).
     *
     * @return {@code true} if complete
     * @throws IOException
     */
    public boolean markComplete() {
        this.isComplete = true;
        return isComplete;
    }

    /**
     * Sets this {@link Task}'s completion status to {@code status}.
     *
     * @param status Completed or not
     */
    public void setCompleteStatus(boolean status) {
        this.isComplete = status;
    }

    /**
     * Returns a character representing the type of {@link Task} ({@link Todo}, {@link Deadline}, or
     * {@link Event}).
     *
     * @return the character representing the task type
     */
    public abstract char getTaskTypeIcon();

    /**
     * Returns this task as an encoded {@link String}, to be written to the data file.
     *
     * @return The encoded String, as a comma-separated value (for easy opening in spreadsheet
     * files)
     */
    public abstract String encode();

    @Override public String toString() {
        return String.format("[%c]\t %s", this.getCompleteIcon(), this.detail);
    }
}