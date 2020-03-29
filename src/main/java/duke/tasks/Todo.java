package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String store() {
        return "T|" + (isDone ? "1" : "0") + "|" + description + "|" + this.tag;
    }

    /**
     * Creates a Todo class.
     * @param strArr Array of String containing input for the Todo class.
     * @return Created Todo class.
     */
    public static Todo create(String[] strArr) {
        Todo t = new Todo(strArr[TASK_NAME_INDEX]);
        if (strArr[IS_DONE_BOOLEAN_INDEX].equals("1")) {
            t.setDone();
        }
        if (strArr.length > 3) {
            String tag = strArr[3];
            t.setTag(tag);
        }
        return t;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + this.returnTag();
    }
}
