package duke.task;

import java.util.Date;

public interface Task {

    public boolean isDone();

    public String getTaskName();

    public String getTaskType();

    public String getDoneString();

    public Date getTaskTime();

    public void markAsDone();
}
