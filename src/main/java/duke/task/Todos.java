package duke.task;

import java.util.Date;

/**
 * Todo object implementing task interface
 */
public class Todos implements Task {

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
     * get done string
     * @return string to represent task state
     */
    @Override
    public String getDoneString() {
        if (isDone()) {
            return "[✓]";
        } else {
            return "[✗]";
        }
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
