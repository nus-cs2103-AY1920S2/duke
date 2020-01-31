/**
 * This class is a type of task.
 */
public class ToDo extends Task{

    /**
     * The constructor of this class.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
        totalTasks++;
    }

    /**
     * Gets the type of the task.
     * @return The type of the task.
     */
    public String getType() {
        return "[T]";
    }

    /**
     * Prints the summary of the task.
     */
    @Override
    public void taskSummary() {
        System.out.println(getType() + getStatusIcon() + " " + description);
    }

    /**
     * Generates a string when saving to the hard disk.
     * @return The string to be written to duke.txt.
     */
    public String saveString() {
        return "T|" + (isDone? "1|" : "0|") + description;
    }


}
