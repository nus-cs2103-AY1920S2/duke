package duke.entity.task;

import java.util.Date;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String printTask() {
        return "[T][" +  (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName();
    }

    @Override
    public boolean isDue(Date date) {
        return false;
    }

    @Override
    public String toStringForm() {
        return "T|" +  (super.isDone() ? "1" : "0" ) + "|"+ super.getTaskName();

    }
}
