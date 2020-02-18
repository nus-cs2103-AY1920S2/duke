package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;

public abstract class Task {
    protected String msg;
    protected String status;
    protected String type;
    protected LocalDate time;

    /**
     * Creates a Task with the details of the task kept in msg.
     * Status of the Task is instantiated to X.
     * @param msg Details of the Task.
     */
    public Task(String msg) throws DukeException {
        this.msg = msg;
        status = "X";
    }

    public void changeDetail(String msg) {
        this.msg = msg;
    }


    public void markDone() {
        status = "O";
    }

    public String getDetails() {
        return msg;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String writeToFile() {
        assert (type.equals("T") | type.equals("D") | type.equals("E")) : "Type should either be T, D or E.";
        return type + " , " + status + " ," + msg;
    }

    @Override
    public String toString() {
        assert (type.equals("T") | type.equals("D") | type.equals("E")) : "Type should either be T, D or E.";
        return "[" + type + "][" + status + "]" + msg;
    }
}
