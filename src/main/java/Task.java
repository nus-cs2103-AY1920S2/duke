/**
 * Class representation of Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Task Constructor.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Task's getStatusIcon method.
     * @return tick or cross dependent on isDone boolean attribute
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public String toSave() {
        return (isDone ? "Y" : "N");
    }

    /**
     * Task's markAsDone method.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    /**
     * Task's overriden toString method.
     * @return String representation of Tasks
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}