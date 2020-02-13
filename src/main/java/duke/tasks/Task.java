package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag = "";
    protected final static int TASK_NAME_INDEX = 2;
    protected final static int IS_DONE_BOOLEAN_INDEX = 1;

    public Task(String s) {
        this.description = s;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✗");
    }

    public String store() {
        return "Task|" + (isDone ? "1" : "0") + "|" + this.description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    protected String returnTag() {
        if (this.tag.length() > 0) {
            return "#" + this.tag;
        }
        return "";
    }

    /**
     * Creates a Task class.
     * @param strArr Array of String containing input for the Task class.
     * @return Created Task class.
     */
    public static Task create(String[] strArr) {
        Task t = new Task(strArr[TASK_NAME_INDEX]);
        if (strArr[1].equals("1")) {
            t.setDone();
        }
        return t;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + ']' + this.description;
    }
}
