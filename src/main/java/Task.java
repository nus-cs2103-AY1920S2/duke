//package duke;

/**
 * Represents a task object characterized by an instrution.
 */
public class Task {
    /**
     * The instrution of this task.
     */
    protected String instruction;
    /**
     * Indicates whether this task is done or not.
     */
    protected boolean isDone;

    /**
     * Creates a new Task with the given instruction.
     * Initially the task is not done.
     */
    public Task(String instruction){
        this.instruction = instruction;
        this.isDone = false;
    }

    /**
     * Gets the instruction of a task.
     * @return the instruction of this task.
     */
    public String getInstruction() {
        return this.instruction;
    }

    /**
     * Gets the status of a task.
     * @return true if this task is done, false if this task is not done yet.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Sets this task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets a string representation of a task.
     * @return the string representation of this tasks.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713]" + instruction;
        } else {
            return "[\u2718]" + instruction;
        }
    }
}
