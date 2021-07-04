import java.time.LocalDate;

/**
 * Parent class of Deadline, Event and ToDo.
 */
public class Task {
    public boolean done;
    public String name;

    /**
     * Constructor. Initialise name and done status.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getClassName() {
        return "Task";
    }

    public LocalDate getDateObj() {
        return null;
    }

    /**
     * Toggle status of the done attribute of task.
     */
    public void toggleDone() {
        this.done = true;
    }

    /**
     * Converts Task into a String to be saved to file.
     * Overwritten by child classes
     * @return String to be saved to file
     */
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "Q , " + doneInt + " , " + name;
    }

    @Override
    public String toString() {
        String symbol;
        if (done) {
            symbol = "O";
        } else {
            symbol = "X";
        }
        return "[" + symbol + "] " + this.name;
    }
}
