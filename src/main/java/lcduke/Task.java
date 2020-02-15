package lcduke;

/** Ths creates a task object.
 */
public abstract class Task {
    private String description;
    protected boolean isDone;
    private String markSymbol;
    protected static int taskNo = 0;

    /** This is the constructor to create the Task Object.
     *
     * @param description Description of user's input.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        taskNo++;
    }

    /** This generates the icon to show if the task is done.
     *
     * @return icon of the task.
     */
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /** This updates the status of the isDone.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /** This returns the status of isDone.
     *
     * @return isDone.
     */
    protected boolean getDone(){
        return isDone;
    }

    /** This returns the description of task.
     *
     * @return description of task
     */
    protected String getDescription(){
        return description;
    }

    protected abstract String printInit();

    /** This generates the description in the list.
     * @return the description in the list
     */
    public String toString(){
        if(isDone) {
            markSymbol  = "\u2713";
        } else {
            markSymbol = "\u2718";
        }
        return ("[" + markSymbol + "] " + description); //return tick or X symbols
    }
}
