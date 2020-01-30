package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String store() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Creates a Todo class.
     * @param strArr Array of String containing input for the Todo class.
     * @return Created Todo class.
     */
    public static Todo create(String[] strArr) {
        Todo t = new Todo(strArr[2]);
        if (strArr[1].equals("1")) {
            t.setDone();
        }
        return t;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
