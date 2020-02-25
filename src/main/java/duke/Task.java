package duke;

/**
 * Represents a task which an user input in the <code>Duke</code> chatbox.
 * A <code>Task</code> consists of 2 components:
 * <code>instr</code> (instructions), which describes the task required to be carried out and
 * <code>done</code>, which describes the status of the task (whether it is completed).
 */
public class Task {
    String instr;
    String type;
    boolean isDone;
    final String tick = "\u2713";
    final String cross = "\u2718";

    public Task(String instr) {
        this.instr = instr;
        this.type = "TASK";
        this.isDone = false;
    }

    /**
     * Changes the <code>Task</code>'s status to <code>done</code> when the task is completed.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Changes the <code>Task</code>'s status to <code>incomplete</code>.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Getter for <code>instr</code> (instruction), the description of the task.
     */
    public String getInstr() {
        return instr;
    }

    /**
     * Getter for <code>type</code> (instruction), the type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Indicates if the task is completed. Returns <code>true</code> when task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + tick + "] " + this.instr;
        } else {
            return "[" + cross + "] " + this.instr;
        }
    }
}
