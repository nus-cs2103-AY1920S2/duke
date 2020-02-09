package lcduke;

/** Ths creates a task object.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String reply;
    protected static int taskNo = 0;

    /** This is the constructor to create the Task Object.
     *
     * @param description Description of user's input.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskNo++;
    }

    /** This generates the icon to show if the task is done.
     *
     * @return icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /** This updates the status of the isDone.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /** This returns the status of isDone.
     *
     * @return isDone.
     */
    public boolean getDone(){
        return isDone;
    }

    /** This returns the description of task.
     *
     * @return description of task
     */
    public String getDescription(){
        return description;
    }

    public abstract void printInit();

    /** This generates the description in the list.
     * @return the description in the list
     */
    public String toString(){
        if(isDone) {
            reply = "\u2713";
        } else {
            reply = "\u2718";
        }
        return ("[" + reply + "] " + description); //return tick or X symbols
    }
}
