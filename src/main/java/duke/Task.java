package duke;

public class Task {
    protected String name;
    protected boolean done;

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
        done = true;
    }




}


