package duke.task;

import duke.utils.Parser;

import java.time.LocalDate;

/**
 * Deadline object extending from ExecutableTask.
 */
public class Deadline extends ExecutableTask {

    private boolean done = false;
    private String name;
    private LocalDate time;

    /**
     * Deadline object constructor.
     * @param name task name
     * @param time task time
     */
    public Deadline(String name, LocalDate time) {
        this.name = name;
        this.time = time;
    }

    /**
     * checks if deadline is done.
     * @return done or not
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     * gets name of deadline.
     * @return task name
     */
    @Override
    public String getTaskName() {
        return name;
    }

    /**
     * gets type of task.
     * @return the string D to represent deadline
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * gets task time.
     * @return task time
     */
    public LocalDate getTaskTime() {
        return time;
    }

    /**
     * marks deadline as done.
     */
    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public void setTaskTime(LocalDate taskTime) {
        this.time = taskTime;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName() + " (by: "
                + Parser.dateToString(getTaskTime()) + ")";
    }
}
