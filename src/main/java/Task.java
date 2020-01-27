import java.io.Serializable;

public class Task implements Serializable {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    /**
     * Gets task desc.
     * @return desc of task
     */
    public String getDesc() {
        return desc;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.desc;
    }
}