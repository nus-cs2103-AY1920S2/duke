/**
 * Represents a Task object. Requires a timing and description for the task to take place. Other classes such as Event,
 * Deadline and Todo extends from this class.
 */

public class Task {

    protected String description;
    protected boolean isDone;
    protected String type = "-";
    protected int index;


    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }


    public void set_Index(int new_index) {
        index = new_index;
    }

    public int get_Index() {
        return index;
    }

    /**
     * Returns 'Y' if the task is done, 'N' if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    /**
     * toString function of the Task object.
     */
    @Override
    public String toString() {
        String temp = "[" + type + "]" + "[" + getStatusIcon() + "] " + description;
        return temp;
    }
}