/**
 * ToDos is a subclass of Task.
 * Represents tasks that have to be done.
 */
public class ToDos extends Task {
    String i;

    /**
     * Constructor that takes in 1 param.
     * @param todo Name of the task.
     */
    public ToDos(String todo) {
        super(todo);
    }

    /**
     * Constructor that takes in 2 params.
     * @param todo Name of the task.
     * @param i Defines the done status of the task. Reads either 1 (complete) or 0 (incomplete).
     */
    public ToDos(String todo, String i) {
        super(todo);
        if (i.equals("1")) {
            this.doneStatus = true;
        } else {
            this.doneStatus = false;
        }
    }

    /**
     * Returns the format to be saved in the output txt file.
     * @return Returns format in as a string.
     */
    @Override
    public String save() {
        int myInt = doneStatus ? 1 : 0;
        return "T , " + myInt + " , " + taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
