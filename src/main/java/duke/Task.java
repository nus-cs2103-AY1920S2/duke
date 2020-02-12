package duke;

/**
 * The type Task.
 */
public class Task {

    private String item;
    private boolean isCompleted;

    /**
     * Instantiates a new Task.
     *
     * @param item the item
     */
    public Task(String item) {
        this.item = item;
        isCompleted = false;
    }

    /**
     * Prints the item added.
     *
     * @return the string
     */
    public String printItemAdded() {
        return item + " has been added to list";
    }

    /**
     * Gets the name of the item.
     *
     * @return the item
     */
    public String getItem() {
        return this.item;
    }

    /**
     * Sets a new name for the item.
     *
     * @param newName input a new name
     */
    public void setItem(String newName) {
        this.item = newName;
    }

    /**
     * Sets the boolean of the task completeness by taking in a boolean.
     *
     * @param isCompleted the is completed
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Sets the boolean of the task completeness by taking in a unicode.
     *
     * @param string the string
     */
    public void setCompleted(String string) {
        if (string.equals("\u2713")) {
            this.isCompleted = true;
        }
    }

    /**
     * Gets the status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + item;
    }

}