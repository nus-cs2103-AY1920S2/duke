/*
 * DukeInvalidTaskFormatException
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The DukeInvalidTaskFormatException class extends the DukeException
 * class and it handles the invalid format of a task from the file.</p>
 * @author Mario Lorenzo
 */

public class DukeInvalidTaskFormatException extends DukeException {
    private String task;
    private int lineIndex;

    /**
     * Constructs the exception instance.
     * @param message The description of the message.
     * @param task The description of the task.
     * @param lineIndex The index of the line.
     */

    public DukeInvalidTaskFormatException(String message, String task, int lineIndex) {
        super(message);
        this.task = task;
        this.lineIndex = lineIndex;
    }

    /**
     * Overrides the Object's toString method to describe the details
     * of the exception.
     * @return The String representing the details of the exception.
     */

    @Override
    public String toString() {
        return getMessage() + "\nTask: " + this.task + "\nLine: " + lineIndex;
    }
}
