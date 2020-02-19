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
     * Method template for Deadline, Event and ToDo
     * @param newDate
     */
    public void snooze(String newDate) {

    }

    /**
     * Method to check if current instance is toDo
     * @return
     */
    public boolean isToDo () {
        return false;
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
