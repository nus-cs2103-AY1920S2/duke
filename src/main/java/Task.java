/**
 * Represents a Task object. Requires a timing and description for the task to take place. Other classes such as Event,
 * Deadline and Todo extends from this class.
 */

public class Task {

    protected String description;
    protected boolean isDone;
    protected String type = "-";
    protected int index;

    /**
     * Constructor for Task object which contains a description as well as a number.
     * When a Task object is first created, it is not done.
     *
     * @param description the description of the task.
     * @param index the index or number associated to the task.
     */
    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }


    public void set_Index(int newIndex) {
        index = newIndex;
    }

    public int get_Index() {
        return index;
    }

    /**
     * Function to return a status icon depending on whether the task is done.
     *
     * @return "Y" if the task is done, "N" if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }


    @Override
    public String toString() {
        String temp = get_Index() + ". " + "[" + type + "]" + "[" + getStatusIcon() + "] " + description;
        return temp;
    }

    public String details() {
        return this.toString();
    }
}