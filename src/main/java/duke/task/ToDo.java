package duke.task;

public class ToDo extends Task {

    /**
     * ToDo constructor.
     * @param  name of the ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Gets the save-string representation of the task.
     * @return the String representation of the task Storage can save.
     */
    @Override
    public String toSaveString() {
        //T1Read a book
        return "T" + (isDone ? "1" : "0") + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
