package entity;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public String printTask() {
        return "[T][" +  (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName();
    }

    public String toStringForm() {
        return "T|" +  (super.isDone() ? "1" : "0" ) + "|"+ super.getTaskName();
    }
}
