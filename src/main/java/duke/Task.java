package duke;

import java.time.LocalDate;

public class Task {
    protected String name;
    protected boolean isDone;

    /***
     * Task constructor
     * @param name
     */
    public Task(String name) {

        this.name = name;
    }

    /***
     * Set a task as done
     */
    protected void setDone() {
        isDone = true;
    }
    protected LocalDate getTime () {
        return null;
    }




}


