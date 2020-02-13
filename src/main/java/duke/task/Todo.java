package duke.task;

import java.time.LocalDate;

/**
 * Todo object extending from ExecutableTask.
 */
public class Todo extends ExecutableTask {

    private boolean done = false;
    private String name;

    /**
     * Constructor for Todo object.
     * @param name name of task
     */
    public Todo(String name) {
        this.name = name;
    }

    /**
     * checks if todo is done.
     * @return done or not
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     * gets name of todo.
     * @return task name
     */
    @Override
    public String getTaskName() {
        return name;
    }

    /**
     * gets type of task.
     * @return the string T to represent todos
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * gets task time.
     * @return null as todos do not have time
     */
    @Override
    public LocalDate getTaskTime() {
        return null;

    }

    /**
     * marks todo as done.
     */
    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public void setTaskTime(LocalDate taskTime) {

    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName();
    }
}
