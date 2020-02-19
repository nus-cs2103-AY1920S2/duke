/**
 * <h1>ToDo Class</h1>
 * Class for ToDo task
 */
public class ToDo extends task {
    /**
     * Class Constructor
     * @param name
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Method to generate string to save to text file
     * @return String
     */
    @Override
    public String toSave() {
        if (this.isDone()) {
            return "T /n 1 /n " + this.getName();
        } else {
            return "T /n 0 /n " + this.getName();
        }
    }

    @Override
    public void snooze(String newDate) {

    }

    /**
     * Return true cause this is toDo;
     * @return
     */
    @Override
    public boolean isToDo() {
        return true;
    }

    /**
     * Generic to print function
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
