package entity;

public class Deadline extends Task {

    private String doBy;

    public Deadline(String taskName, String doBy) {
        super(taskName);
        this.doBy = doBy;
    }

    public String getDoBy() {
        return doBy;
    }

    public void setDoBy(String doBy) {
        this.doBy = doBy;
    }

    public String printTask() {
        return "[D][" +  (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName() + "(by: " + doBy + ")";
    }

    public String toStringForm() {
        return "D|" +  (super.isDone() ? "1" : "0" ) + "|"+ super.getTaskName() + "|" + doBy;
    }
}
