/** ToDo is a child class of Task.
 * Task without any date/time attached to it*/

public class ToDo extends Task {
    String time;

    /**
     * Constructor that takes in 1 param.
     *
     * @param name Name of ToDo.
     */
    public ToDo(String name) {
        super(name);
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getClassName() {
        return "todo";
    }

    /**
     * Converts ToDo into a String to be saved to file.
     * @return String to be saved to file
     */
    @Override
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "T , " + doneInt + " , " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
