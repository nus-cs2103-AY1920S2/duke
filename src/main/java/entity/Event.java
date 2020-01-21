package entity;

public class Event extends Task {

    private String heldAt;

    public Event(String taskName, String heldAt) {
        super(taskName);
        this.heldAt = heldAt;
    }

    public String heldAt() {
        return heldAt;
    }

    public void setHeldAt(String heldAt) {
        this.heldAt = heldAt;
    }

    public String printTask() {
        return "[E][" +  (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName() + "(at: " + heldAt + ")";
    }
}
