package Duke.task;

import Duke.Duke;
import Duke.exception.DukeException;

import java.io.Serializable;

public interface Task extends Serializable {
    public static boolean isValidTask(String task) {
        return task.equals("todo") || task.equals("event") || task.equals("deadline");
    }

    public boolean markDone() throws DukeException;

    public String getTaskType();
}