package duke.task;

import java.util.Date;

/**
 * Todos object extending from ExecutableTask
 */
public class Todos extends ExecutableTask {

    private boolean done = false;
    private String name;

    /**
     * Constructor for Todos object
     * @param name name of task
     */
    public Todos(String name) {
        this.name = name;
    }

    /**
     * check if todos is done
     * @return done or not
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     * get name of todos
     * @return task name
     */
    @Override
    public String getTaskName() {
        return name;
    }

    /**
     * get type of task
     * @return the string T to represent todos
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * get task time
     * @return null as todos do not have time
     */
    @Override
    public Date getTaskTime() {
        return null;

    }

    /**
     * mark todos as done
     */
    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName();
    }
}
