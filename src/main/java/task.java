/**
 * <h1>Task Class</h1>
 * Super Class for Deadline, Event and ToDo classes
 */
public class task {
    private String status;
    private String Name;

    /**
     * Class Constructor
     * @param name
     */
    public task(String name) {
        this.Name = name;
        this.status = "\u2718";
    }

    /**
     * Method returns task Name
     * @return String
     */
    public String getName() {
        return this.Name;
    }

    /**
     * Method checks if Task is done
     * @return Boolean
     */
    public boolean isDone() {
        if (this.status.equals("\u2718")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method sets Task as done
     */
    public void markDone() {
        this.status = "\u2713";
    }

    /**
     * Method template for Deadline, Event and ToDo
     * @return String
     */
    public String toSave() {
        String s = "";
        return s;
    }

    /**
     * Generic toString method
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.status + "] " + this.Name;
    }
}
